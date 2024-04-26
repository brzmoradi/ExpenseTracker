package com.behrooz.expensetracker.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageResourceConfig {

  @Bean
  public MessageSource messageSource() {
    var messageResource = new ReloadableResourceBundleMessageSource();
    messageResource.setBasename("classpath:/message/messages");
    messageResource.setDefaultEncoding("UTF-8");
    return messageResource;
  }
}
