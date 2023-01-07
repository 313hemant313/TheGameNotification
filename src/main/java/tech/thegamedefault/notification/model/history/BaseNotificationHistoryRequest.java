package tech.thegamedefault.notification.model.history;

import tech.thegamedefault.notification.utility.CommonConstants.Status;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BaseNotificationHistoryRequest {

  String appId;

  @NotBlank(message = "message id is mandatory.")
  String messageId;

  String content;
  String templateName;
  String templateData;

  String notificationRequest;
  Status status;
  String errorMessage;
  String thirdPartyResponse;

}
