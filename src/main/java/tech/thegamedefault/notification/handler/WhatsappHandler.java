package tech.thegamedefault.notification.handler;

import tech.thegamedefault.notification.framework.NotificationHandler;
import tech.thegamedefault.notification.model.NotificationHandlerResponse;
import tech.thegamedefault.notification.model.NotificationPayload;
import tech.thegamedefault.notification.model.WhatsappNotificationPayload;
import tech.thegamedefault.notification.model.history.BaseNotificationHistoryRequest;
import tech.thegamedefault.notification.model.history.WhatsappHistoryRequest;
import tech.thegamedefault.notification.service.WhatsappNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WhatsappHandler extends NotificationHandler {

  @Autowired
  public WhatsappHandler(WhatsappNotificationService notificationService) {
    super(notificationService);
  }

  @Override
  protected BaseNotificationHistoryRequest buildHistoryRequest(String messageId,
      NotificationPayload payload, NotificationHandlerResponse response) {
    WhatsappNotificationPayload whatsappNotificationPayload = (WhatsappNotificationPayload) payload;
    WhatsappHistoryRequest request = super.buildWithBaseRequest(messageId, payload, response,
        WhatsappHistoryRequest.class);
    request.setDestination(whatsappNotificationPayload.getDestination());
    return request;
  }

}