package com.programacaoweb.filmes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigs {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                      auth.requestMatchers("/", "/index", "/login").permitAll();
                      auth.requestMatchers(HttpMethod.POST, "/login").permitAll();
                      auth.requestMatchers("/admin", "/cadastro", "/salvar", "/editar/*", "/deletar/*", "/restaurar/*").hasRole("ADMIN");
                      auth.requestMatchers("/verCarrinho", "/adicionarCarrinho", "/finalizarCompra").hasRole("USER");
                      auth.anyRequest().permitAll();
                  })
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll())
                .logout(logout -> logout
                        .permitAll()
                )
                .httpBasic(httpBasic -> httpBasic.disable()).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
