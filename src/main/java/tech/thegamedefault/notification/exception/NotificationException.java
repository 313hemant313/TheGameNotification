package tech.thegamedefault.notification.exception;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class NotificationException extends RuntimeException {

  private final String message;
  private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
  private int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
  private List<String> errors;

  public NotificationException(String message) {
    this.message = message;
  }

  public NotificationException(String message, List<String> errors) {
    this.message = message;
    this.errors = errors;
  }

  public NotificationException(String message, HttpStatus status) {
    this.message = message;
    this.status = status;
    this.statusCode = status.value();
  }

  public NotificationException(String message, HttpStatus status, List<String> errors) {
    this.message = message;
    this.status = status;
    this.statusCode = status.value();
    this.errors = errors;
  }

  public ResponseEntity<NotificationError> buildNotificationErrorResponse() {
    NotificationError error = new NotificationError();
    error.setErrors(this.getErrors());
    error.setMessage(this.getMessage());
    error.setStatus(this.getStatus());
    error.setStatusCode(this.getStatusCode());
    return new ResponseEntity<>(error, this.getStatus());
  }

}
