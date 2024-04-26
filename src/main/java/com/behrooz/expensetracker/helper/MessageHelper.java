package com.behrooz.expensetracker.helper;

import com.behrooz.expensetracker.exception.model.ApplicationException;
import com.behrooz.expensetracker.exception.model.ErrorResponse;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageHelper {

  @Value("${default.message.undefine:'message not resolved'}")
  private String defaultMessage;

  @Value("${default.message.undefine.code:9999}")
  private Integer defaultMessageCode;

  private final MessageSource messageSource;

  public String getMessage(String messageCode, Object... args) {
    return messageSource.getMessage(messageCode, args, defaultMessage, Locale.getDefault());
  }

  public ErrorResponse translateException(ApplicationException e) {
    log.error("Error Happened: ", e);
    String message = this.getMessage(e.getClass().getName(), e.getParams());
    return parseErrorMessage(message);
  }

  public ErrorResponse parseErrorMessage(String errorMessage) {
    String[] errorMessageItems = errorMessage.split("#");
    return ErrorResponse.builder()
        .message(errorMessageItems[0])
        .code(Integer.parseInt(errorMessageItems[1]))
        .build();
  }

  public ErrorResponse translateException(Exception e) {
    log.error("Error Happened: ", e);
    try {
      return parseErrorMessage(this.getMessage(e.getClass().getName()));
    } catch (Exception ex) {
      log.error("Error in translate Error message ", ex);
      return getGeneralErrorMessage();
    }
  }

  public ErrorResponse errorMessage(String errorMessage, Object... args) {
    String messageResponse = getMessage(errorMessage, args);
    String[] errorMessageItems = messageResponse.split("#");
    return ErrorResponse.builder()
        .message(errorMessageItems[0])
        .code(Integer.parseInt(errorMessageItems[1]))
        .build();
  }

  public ErrorResponse getGeneralErrorMessage() {
    return ErrorResponse.builder().message(defaultMessage).code(defaultMessageCode).build();
  }
}
