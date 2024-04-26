package com.behrooz.expensetracker.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("api")
public class ApplicationProperties {

  private String version;
  private String title;
  private String description;
}
