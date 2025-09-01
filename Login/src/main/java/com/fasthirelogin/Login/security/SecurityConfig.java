package com.fasthirelogin.Login.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    // ✅ Inject JwtAuthFilter bean
    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Public APIs
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/superadmin/createSuperAdmin").permitAll() // ✅ allow creating SuperAdmin without JWT

                        // Protected APIs (need JWT + permissions)
                        .requestMatchers("/superadmin/getAllSuperAdmins").hasAuthority("CAN_READ")
                        .requestMatchers("/superadmin/updateSuperAdmin/**").hasAuthority("CAN_UPDATE")
                        .requestMatchers("/superadmin/deleteSuperAdmin/**").hasAuthority("CAN_DELETE")

                        // Everything else
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // ✅ add JWT filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }
}
