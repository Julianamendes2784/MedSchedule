package org.example.medschedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuração de segurança da API (Spring Security).
 *
 * Nesta fase do projeto a segurança ainda não foi construída, então todas as rotas
 * (incluindo o Swagger) ficam abertas, sem exigir usuário e senha.
 */
@Configuration // Diz ao Spring que esta classe declara beans de configuração.
public class SecurityConfig {

    /**
     * Bean que monta a "cadeia de filtros" de segurança aplicada a todo request HTTP.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // CSRF é uma proteção pensada para sites com sessão/cookie no navegador. Por padrão o
            // Spring Security exige um token CSRF em todo POST/PUT/PATCH/DELETE; como o Postman/Swagger
            // não enviam esse token, esses verbos seriam recusados. Em uma API REST sem sessão
            // é correto desabilitar.
            .csrf(csrf -> csrf.disable())
            // Libera todas as rotas sem autenticação (a autenticação será implementada em uma fase futura).
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}
