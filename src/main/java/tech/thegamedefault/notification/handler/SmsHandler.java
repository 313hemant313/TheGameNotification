package tech.thegamedefault.notification.handler;

import tech.thegamedefault.notification.framework.NotificationHandler;
import tech.thegamedefault.notification.model.NotificationHandlerResponse;
import tech.thegamedefault.notification.model.NotificationPayload;
import tech.thegamedefault.notification.model.history.SmsHistoryRequest;
import tech.thegamedefault.notification.service.SmsNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsHandler extends NotificationHandler {

  @Autowired
  public SmsHandler(SmsNotificationService notificationService) {
    super(notificationService);
  }

  @Override
  protected SmsHistoryRequest buildHistoryRequest(String messageId,
      NotificationPayload payload, NotificationHandlerResponse response) {
    SmsHistoryRequest request = super.buildWithBaseRequest(messageId, payload, response,
        SmsHistoryRequest.class);
    request.setDestinations(payload.getDestinations());
    return request;
  }

}
