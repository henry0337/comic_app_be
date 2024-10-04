package com.henry.demo.conf;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Lớp cấu hình OpenAPI
 * @see <a href="https://github.com/oai/openapi-specification/blob/main/versions/3.1.0.md">OpenAPI Specification</a>
 */
@Configuration
public class OpenAPIConfiguration {

    /**
     * Phiên bản của OpenAPI Specification.
     * @apiNote Chỉ có thể là 1 trong 2 phiên bản sau: {@code 3.0.1}, {@code 3.1.0}
     */
    private static final String OPENAPI_VERSION = "3.1.0";

    /**
     * Tiêu đề của API.
     */
    private static final String TITLE = "ComicApp";

    /**
     * Phiên bản hiện tại của API.<br> Thường được sử dụng với các VCS để quản lý phiên bản API.
     */
    private static final String API_VERSION = "1.0";

    @Bean
    public OpenAPI customizeOpenAPI() {
        return new OpenAPI()
                .openapi(OPENAPI_VERSION)
                .components(new Components().addSecuritySchemes(
                        "Bearer Token",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT")
                                .scheme("bearer")
                ))
                .info(new Info().title(TITLE).version(API_VERSION));
    }
}
