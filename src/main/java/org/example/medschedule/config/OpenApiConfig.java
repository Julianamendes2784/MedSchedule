package org.example.medschedule.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração da documentação Swagger/OpenAPI.
 *
 * Declara que a API usa autenticação HTTP Basic. Com isso o Swagger UI exibe o botão
 * "Authorize": basta informar usuário e senha uma vez e todas as requisições de teste
 * passam a enviar as credenciais (sem depender do popup do navegador).
 */
@Configuration
@OpenAPIDefinition(security = @SecurityRequirement(name = "basicAuth"))
@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
public class OpenApiConfig {
}
