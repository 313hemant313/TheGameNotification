package tech.thegamedefault.notification.framework;

import tech.thegamedefault.notification.handler.EmailHandler;
import tech.thegamedefault.notification.handler.SmsHandler;
import tech.thegamedefault.notification.handler.WhatsappHandler;
import tech.thegamedefault.notification.model.EmailNotificationPayload;
import tech.thegamedefault.notification.model.NotificationPayload;
import tech.thegamedefault.notification.model.SmsNotificationPayload;
import tech.thegamedefault.notification.model.WhatsappNotificationPayload;

public enum NotificationType {

  SMS(SmsHandler.class, SmsNotificationPayload.class, "sms", "Notification"),
  EMAIL(EmailHandler.class, EmailNotificationPayload.class, "email", "Notification"),
  WHATSAPP(WhatsappHandler.class, WhatsappNotificationPayload.class, null, "Notification");

  private final Class<? extends NotificationHandler> handlerClass;
  private final Class<? extends NotificationPayload> payloadClass;
  private final String templateDirectory;
  private final String topicName;

  NotificationType(Class<? extends NotificationHandler> handlerClass,
      Class<? extends NotificationPayload> payloadClass, String templateDirectory,
      String topicName) {
    this.handlerClass = handlerClass;
    this.payloadClass = payloadClass;
    this.templateDirectory = templateDirectory;
    this.topicName = topicName;
  }

  public Class<? extends NotificationHandler> getHandlerClass() {
    return handlerClass;
  }

  public String getTopicName() {
    return topicName;
  }

  public String getTemplateDirectory() {
    return templateDirectory;
  }

  public Class<? extends NotificationPayload> getPayloadClass() {
    return payloadClass;
  }
}
