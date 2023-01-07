package tech.thegamedefault.notification.exception;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class NotificationError {

  private String message;
  private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
  private int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
  private List<String> errors;

}
