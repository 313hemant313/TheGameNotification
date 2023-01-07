package tech.thegamedefault.notification.service;

import tech.thegamedefault.notification.entity.EmailHistory;
import tech.thegamedefault.notification.exception.NotificationException;
import tech.thegamedefault.notification.framework.INotificationService;
import tech.thegamedefault.notification.model.NotificationHandlerResponse;
import tech.thegamedefault.notification.model.NotificationPayload;
import tech.thegamedefault.notification.model.history.BaseNotificationHistoryRequest;
import tech.thegamedefault.notification.model.history.EmailHistoryRequest;
import tech.thegamedefault.notification.repository.EmailHistoryRepository;
import tech.thegamedefault.notification.service.pinpoint.EmailPinpointNotification;
import tech.thegamedefault.notification.utility.CommonConstants;
import tech.thegamedefault.notification.utility.NotificationHistoryUtility;
import tech.thegamedefault.notification.utility.ObjectMapperUtility;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements INotificationService {

  @Autowired
  NotificationTemplateEngine templateEngine;

  @Autowired
  EmailPinpointNotification pinpointNotification;

  @Autowired
  EmailHistoryRepository repository;

  @Override
  public void sendNotification(NotificationPayload request, NotificationHandlerResponse response) {
    Optional.ofNullable(request.getTemplatePayload()).ifPresent(templatePayload -> {
      String content = templateEngine.process(templatePayload.getTemplateName(),
          request.getNotificationType().getTemplateDirectory(), templatePayload.getData());
      request.setMessage(content);
    });
    pinpointNotification.sendNotification(request, response);
  }

  public void createHistory(BaseNotificationHistoryRequest request) {
    EmailHistoryRequest emailHistoryRequest = (EmailHistoryRequest) request;
    EmailHistory emailHistory = NotificationHistoryUtility.buildEmailHistory(emailHistoryRequest);
    repository.save(emailHistory);
  }

  public void updateHistory(BaseNotificationHistoryRequest request) {
    String messageId = request.getMessageId();
    EmailHistory emailHistory = getEmailHistory(messageId);
    repository.save(
        Objects.requireNonNull(ObjectMapperUtility.updateObject(request, emailHistory)));
  }

  public EmailHistory getEmailHistory(String messageId) {
    return repository.findByMessageId(messageId).orElseThrow(
        () -> new NotificationException(CommonConstants.ErrorMessage.NOT_FOUND.getMessage(),
            HttpStatus.NOT_FOUND));
  }

}
