package tech.thegamedefault.notification.model;

import tech.thegamedefault.notification.framework.NotificationType;
import tech.thegamedefault.notification.utility.CommonConstants.Language;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class NotificationPayload {

  NotificationType notificationType;
  String appId;
  List<String> destinations = new ArrayList<>(); // For Whatsapp comm, destination will be used.
  String message;

  TemplatePayload templatePayload;

  Language language = Language.ENGLISH;

}
