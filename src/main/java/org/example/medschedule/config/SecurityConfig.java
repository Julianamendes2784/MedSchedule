package org.example.medschedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configuração de segurança da API (Spring Security).
 *
 * Define QUEM pode acessar QUAIS rotas. Aqui usamos autenticação HTTP Basic:
 * o cliente (Postman/Swagger) envia usuário e senha em todo request, e o usuário
 * válido é o definido em application.properties (spring.security.user.*).
 */
@Configuration // Diz ao Spring que esta classe declara beans de configuração.
public class SecurityConfig {

    // Libera Swagger/OpenAPI sem login enquanto a fase de Seguranca ainda nao foi construida.
    /**
     * Bean que monta a "cadeia de filtros" de segurança aplicada a todo request HTTP.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // CSRF é uma proteção pensada para sites com sessão/cookie no navegador. Por padrão o
            // Spring Security exige um token CSRF em todo POST/PUT/PATCH/DELETE; como o Postman/Swagger
            // não enviam esse token, esses verbos voltariam com erro (401) mesmo com login correto.
            // Em uma API REST autenticada por Basic (sem cookie de sessão) é correto desabilitar.
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Rotas públicas: a documentação do Swagger abre sem login.
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**"
                ).permitAll()
                // Qualquer outra rota (/pacientes, /usuarios, ...) exige estar autenticado.
                .anyRequest().authenticated()
            )
            // Ativa autenticação HTTP Basic (usuário:senha no cabeçalho Authorization).
            .httpBasic(withDefaults());
        return http.build();
    }
}
