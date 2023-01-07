package tech.thegamedefault.notification.service;

import tech.thegamedefault.notification.entity.SmsHistory;
import tech.thegamedefault.notification.exception.NotificationException;
import tech.thegamedefault.notification.framework.INotificationService;
import tech.thegamedefault.notification.model.NotificationHandlerResponse;
import tech.thegamedefault.notification.model.NotificationPayload;
import tech.thegamedefault.notification.model.history.BaseNotificationHistoryRequest;
import tech.thegamedefault.notification.model.history.SmsHistoryRequest;
import tech.thegamedefault.notification.repository.SmsHistoryRepository;
import tech.thegamedefault.notification.service.pinpoint.SmsPinpointNotification;
import tech.thegamedefault.notification.utility.CommonConstants;
import tech.thegamedefault.notification.utility.ObjectMapperUtility;
import tech.thegamedefault.notification.utility.NotificationHistoryUtility;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SmsNotificationService implements INotificationService {

  @Autowired
  SmsPinpointNotification pinpointNotification;

  @Autowired
  SmsHistoryRepository repository;

  @Override
  public void sendNotification(NotificationPayload request, NotificationHandlerResponse response) {
    pinpointNotification.sendNotification(request, response);
  }

  public void createHistory(BaseNotificationHistoryRequest request) {
    SmsHistoryRequest smsHistoryRequest = (SmsHistoryRequest) request;
    SmsHistory smsHistory = NotificationHistoryUtility.buildSmsHistory(smsHistoryRequest);
    repository.save(smsHistory);
  }

  public void updateHistory(BaseNotificationHistoryRequest request) {
    String messageId = request.getMessageId();
    SmsHistory smsHistory = getSmsHistory(messageId);
    repository.save(Objects.requireNonNull(ObjectMapperUtility.updateObject(request, smsHistory)));
  }

  public SmsHistory getSmsHistory(String messageId) {
    return repository.findByMessageId(messageId).orElseThrow(
        () -> new NotificationException(CommonConstants.ErrorMessage.NOT_FOUND.getMessage(),
            HttpStatus.NOT_FOUND));
  }

}
