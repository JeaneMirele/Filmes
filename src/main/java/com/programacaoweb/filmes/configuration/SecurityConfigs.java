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
                        auth.requestMatchers("/admin", "/cadastro", "/salvar", "/editar/*", "/deletar/*", "/restaurar/*").hasRole("ROLE_ADMIN");
                        auth.requestMatchers("/vercarrinho", "/adicionarcarrinho", "/finalizarcompra").hasRole("ROLE_USER");
                        auth.anyRequest().permitAll();
                    })
                .formLogin(login -> login.loginPage("/login"))
                .logout(l -> {
                      l.logoutUrl("/logout");
                      l.clearAuthentication(true);
                      l.deleteCookies().invalidateHttpSession(true);
                      })
                 .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
