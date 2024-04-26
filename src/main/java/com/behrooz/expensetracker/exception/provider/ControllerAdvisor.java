package com.behrooz.expensetracker.exception.provider;

import com.behrooz.expensetracker.exception.model.BusinessException;
import com.behrooz.expensetracker.exception.model.ErrorResponse;
import com.behrooz.expensetracker.exception.model.NotFoundException;
import com.behrooz.expensetracker.exception.model.UnAuthorizedException;
import com.behrooz.expensetracker.helper.MessageHelper;
import com.behrooz.expensetracker.util.StringUtil;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@ResponseBody
@Slf4j
@RequiredArgsConstructor
public class ControllerAdvisor {

    private final MessageHelper messageHelper;


    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handle(UnAuthorizedException ex) {
        return messageHelper.translateException(ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception ex) {
        return messageHelper.translateException(ex);
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse userAccess(AccessDeniedException ex) {
        return messageHelper.translateException(ex);
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse defaultHandle(NotFoundException ex) {
        return messageHelper.translateException(ex);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse businessExceptionHandle(BusinessException ex) {
        return messageHelper.translateException(ex);
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse tokenExceptionHandle(
            org.springframework.dao.DataIntegrityViolationException ex) {
        return translateError(ex);
    }

    @ExceptionHandler(
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handle(
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException exception) {
        log.error("exception Happened ", exception);
        return messageHelper.errorMessage(
                "method.argument.type.mismatch", new Object[] {exception.getValue()});
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse handle(MethodArgumentNotValidException e) {
        Optional<String> message =
                e.getBindingResult().getFieldErrors().stream()
                        .map(
                                fieldError ->
                                        messageHelper.getMessage(
                                                Objects.requireNonNull(fieldError.getDefaultMessage()),
                                                List.of(fieldError.getField()).toArray()))
                        .distinct()
                        .reduce((a1, a2) -> a1 + "  ,  " + a2);

        return ErrorResponse.builder()
                .message(message.orElse("no message set for constraint"))
                .code(Integer.parseInt(messageHelper.getMessage("method.argument.validation.error.code")))
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleInternalServerError(RuntimeException e) {
        log.error("Exception Happened: ", e);
        String[] errorMessage =
                messageHelper
                        .getMessage("internal.server.error.message", null, Locale.getDefault())
                        .split("#");

        return ErrorResponse.builder()
                .code(Integer.parseInt(errorMessage[1]))
                .message(errorMessage[0])
                .build();
    }

    private ErrorResponse translateError(DataIntegrityViolationException ex) {
        return ErrorResponse.builder()
                .code(
                        Integer.parseInt(messageHelper.getMessage("constraint.validation.database.error.code")))
                .message(
                        messageHelper.getMessage(StringUtil.getConstraintName(Objects.requireNonNull(ex.getRootCause()).getMessage())))
                .build();
    }

    private Set<Pair<String, String>> buildValidationErrors(Set<ConstraintViolation<?>> violations) {
        return violations.stream()
                .map(
                        violation ->
                                Pair.of(
                                        violation.getMessage(),
                                        Objects.requireNonNull(
                                                        StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
                                                                .reduce((first, second) -> second)
                                                                .orElse(null))
                                                .toString()))
                .collect(Collectors.toSet());
    }
}
