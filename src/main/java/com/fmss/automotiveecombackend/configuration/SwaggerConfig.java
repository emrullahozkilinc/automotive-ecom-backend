package com.fmss.automotiveecombackend.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components()
                .addSecuritySchemes("spring_oauth", new SecurityScheme()
                    .type(SecurityScheme.Type.OAUTH2)
                    .description("Oauth2 flow")
                    .flows(new OAuthFlows()
                        .password(new OAuthFlow()
                            .tokenUrl("http://localhost:8091/auth/realms/automotive-ecom/protocol/openid-connect/token")
                            .authorizationUrl("http://localhost:8091/auth/realms/automotive-ecom/protocol/openid-connect/authorize")
                        )))
            )
            .security(Collections.singletonList(new SecurityRequirement().addList("spring_oauth")));
    }
}
