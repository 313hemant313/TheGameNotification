package tech.thegamedefault.notification.exception;

import tech.thegamedefault.notification.utility.CommonConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotificationExceptionHandler {

  private static final String DEFAULT_ERROR_MESSAGE = CommonConstants.ErrorMessage.SOMETHING_WENT_WRONG
      .getMessage();

  @ExceptionHandler(NotificationException.class)
  public ResponseEntity<NotificationError> handleException(NotificationException e) {
    return e.buildNotificationErrorResponse();
  }

  @ExceptionHandler({
      BindException.class,
      MethodArgumentNotValidException.class
  })
  public ResponseEntity<NotificationError> handleException(BindException e) {
    List<String> errors = new ArrayList<>();
    e.getFieldErrors().forEach(err -> errors.add(err.getField() + ": " + err.getDefaultMessage()));
    e.getGlobalErrors()
        .forEach(err -> errors.add(err.getObjectName() + ": " + err.getDefaultMessage()));
    return new NotificationException(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, errors)
        .buildNotificationErrorResponse();
  }

  @ExceptionHandler({HttpMessageNotReadableException.class})
  public ResponseEntity<NotificationError> handleException(HttpMessageNotReadableException e) {
    return new NotificationException(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST)
        .buildNotificationErrorResponse();
  }

  @ExceptionHandler({Exception.class, RuntimeException.class})
  public ResponseEntity<NotificationError> handleException(Exception e) {
    List<String> errors = Collections
        .singletonList(String.format("%s: %s", e.getClass(), e.getLocalizedMessage()));
    return new NotificationException(DEFAULT_ERROR_MESSAGE, errors).buildNotificationErrorResponse();
  }

}
