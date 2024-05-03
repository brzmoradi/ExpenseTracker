package com.behrooz.expensetracker.config.swagger;

import com.behrooz.expensetracker.config.ApplicationProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

  private final ApplicationProperties applicationProperties;

  @Value("${spring.application.name:expense-tracking}")
  private String groupName;

  @Bean
  public OpenAPI springShopOpenAPI() {
    final String securitySchemeName = "Token";
    return new OpenAPI()
        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
        .components(
            new Components()
                .addSecuritySchemes(
                    securitySchemeName,
                    new SecurityScheme()
                        .name(securitySchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")))
        .info(
            new Info()
                .title(groupName)
                .description(applicationProperties.getDescription())
                .version(applicationProperties.getVersion())
                .license(new License().name(applicationProperties.getTitle())));
  }
}
