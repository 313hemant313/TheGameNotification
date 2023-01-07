package tech.thegamedefault.notification.framework;

import tech.thegamedefault.notification.model.NotificationPayload;
import tech.thegamedefault.notification.utility.ApplicationContextUtility;
import tech.thegamedefault.notification.utility.ObjectMapperUtility;

public class NotificationHandlerFactory {

  public static NotificationHandler getHandler(NotificationPayload request) {
    return ApplicationContextUtility.getBean(request.getNotificationType().getHandlerClass());
  }

  public static NotificationPayload getPayload(String payload, NotificationType notificationType) {
    return ObjectMapperUtility.readValue(payload, notificationType.getPayloadClass());
  }

  NotificationHandlerFactory() {

  }

}
