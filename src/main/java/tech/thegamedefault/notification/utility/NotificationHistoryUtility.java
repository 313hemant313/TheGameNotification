package tech.thegamedefault.notification.utility;

import tech.thegamedefault.notification.entity.EmailHistory;
import tech.thegamedefault.notification.entity.SmsHistory;
import tech.thegamedefault.notification.entity.WhatsappHistory;
import tech.thegamedefault.notification.model.history.EmailHistoryRequest;
import tech.thegamedefault.notification.model.history.SmsHistoryRequest;
import tech.thegamedefault.notification.model.history.WhatsappHistoryRequest;

public class NotificationHistoryUtility {

  public static SmsHistory buildSmsHistory(SmsHistoryRequest smsHistoryRequest) {
    return ObjectMapperUtility.convertValue(smsHistoryRequest, SmsHistory.class);
  }

  public static EmailHistory buildEmailHistory(
      EmailHistoryRequest emailHistoryRequest) {
    return ObjectMapperUtility.convertValue(emailHistoryRequest, EmailHistory.class);
  }

  public static WhatsappHistory buildWhatsappHistory(
      WhatsappHistoryRequest whatsappHistoryRequest) {
    return ObjectMapperUtility.convertValue(whatsappHistoryRequest, WhatsappHistory.class);
  }

  private NotificationHistoryUtility() {

  }

}
