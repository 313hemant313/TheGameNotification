package tech.thegamedefault.notification.model.history;

import lombok.Data;

@Data
public class EmailHistoryRequest extends BaseNotificationHistoryRequest {

  String subject;
  String htmlBody;

}
