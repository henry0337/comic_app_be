package com.henry.demo.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.henry.demo.infrastructure.constant.OpenAPIConstant.*;

/**
 * Lớp cấu hình OpenAPI
 *
 * @see <a href="https://github.com/oai/openapi-specification/blob/main/versions/3.1.0.md">OpenAPI Specification</a>
 */
@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI customizeOpenAPI() {
        return new OpenAPI()
                .openapi(OPENAPI_VERSION)
                .info(new Info().title(TITLE).version(API_VERSION))
                .components(new Components().addSecuritySchemes(
                        "Bearer Token",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT")
                                .scheme("bearer")
                ));
    }
}
