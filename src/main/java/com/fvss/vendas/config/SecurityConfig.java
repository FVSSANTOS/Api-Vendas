package com.fvss.vendas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fvss.vendas.security.jwt.JwtAuthFilter;
import com.fvss.vendas.security.jwt.JwtService;
import com.fvss.vendas.service.impl.UsuarioServiceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsuarioServiceImpl usuarioService;
    
    
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
            .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
            .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }
}
