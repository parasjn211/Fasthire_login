package com.fasthirelogin.Login.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.extractEmail(token);

                // ✅ Extract role & permissions
                String role = jwtUtil.extractClaim(token, "role", String.class);
                boolean canCreate = jwtUtil.extractClaim(token, "canCreate", Boolean.class);
                boolean canUpdate = jwtUtil.extractClaim(token, "canUpdate", Boolean.class);
                boolean canDelete = jwtUtil.extractClaim(token, "canDelete", Boolean.class);
                boolean canRead   = jwtUtil.extractClaim(token, "canRead", Boolean.class);

                List<GrantedAuthority> authorities = new ArrayList<>();
                if (canCreate) authorities.add(() -> "CAN_CREATE");
                if (canUpdate) authorities.add(() -> "CAN_UPDATE");
                if (canDelete) authorities.add(() -> "CAN_DELETE");
                if (canRead)   authorities.add(() -> "CAN_READ");

                // Also add role itself
                authorities.add(() -> "ROLE_" + role);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(email, null, authorities);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
