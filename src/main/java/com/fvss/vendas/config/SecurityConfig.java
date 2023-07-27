package com.fvss.vendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> 
              auth
                .requestMatchers(HttpMethod.POST,"/api/usuarios/**")
                    .permitAll()
                .requestMatchers("/api/clientes/**")
                    .hasAnyRole("USER","ADMIN")
                .requestMatchers("/api/produtos/**")
                    .hasRole("ADMIN")
                .requestMatchers("/api/pedidos/**")
                    .hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
