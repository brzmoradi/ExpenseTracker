package com.behrooz.expensetracker.config.swagger;

import com.behrooz.expensetracker.config.ApplicationProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
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
    return new OpenAPI()
        .info(
            new Info()
                .title(groupName)
                .description(applicationProperties.getDescription())
                .version(applicationProperties.getVersion())
                .license(new License().name(applicationProperties.getTitle())));
  }
}
