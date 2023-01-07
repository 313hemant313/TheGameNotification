package tech.thegamedefault.notification.handler;

import tech.thegamedefault.notification.framework.NotificationHandler;
import tech.thegamedefault.notification.model.EmailNotificationPayload;
import tech.thegamedefault.notification.model.NotificationHandlerResponse;
import tech.thegamedefault.notification.model.NotificationPayload;
import tech.thegamedefault.notification.model.history.BaseNotificationHistoryRequest;
import tech.thegamedefault.notification.model.history.EmailHistoryRequest;
import tech.thegamedefault.notification.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailHandler extends NotificationHandler {

  @Autowired
  public EmailHandler(EmailNotificationService notificationService) {
    super(notificationService);
  }

  @Override
  protected BaseNotificationHistoryRequest buildHistoryRequest(String messageId,
      NotificationPayload payload, NotificationHandlerResponse response) {
    EmailNotificationPayload emailNotificationPayload = (EmailNotificationPayload) payload;
    EmailHistoryRequest request = super.buildWithBaseRequest(messageId, payload, response,
        EmailHistoryRequest.class);
    request.setSubject(emailNotificationPayload.getSubject());
    return request;
  }

}

