package tech.thegamedefault.notification.service;

import tech.thegamedefault.notification.connector.WhatsappHttpConnector;
import tech.thegamedefault.notification.entity.WhatsappHistory;
import tech.thegamedefault.notification.exception.NotificationException;
import tech.thegamedefault.notification.framework.INotificationService;
import tech.thegamedefault.notification.model.NotificationHandlerResponse;
import tech.thegamedefault.notification.model.NotificationPayload;
import tech.thegamedefault.notification.model.WhatsappNotificationPayload;
import tech.thegamedefault.notification.model.history.BaseNotificationHistoryRequest;
import tech.thegamedefault.notification.model.history.WhatsappHistoryRequest;
import tech.thegamedefault.notification.model.thirdparty.WhatsappText;
import tech.thegamedefault.notification.model.thirdparty.WhatsappTextMessageRequest;
import tech.thegamedefault.notification.model.thirdparty.response.WhatsappMessageResponse;
import tech.thegamedefault.notification.repository.WhatsappHistoryRepository;
import tech.thegamedefault.notification.utility.CommonConstants;
import tech.thegamedefault.notification.utility.NotificationHistoryUtility;
import tech.thegamedefault.notification.utility.ObjectMapperUtility;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

@Service
@Slf4j
public class WhatsappNotificationService implements INotificationService {

  @Autowired
  WhatsappHttpConnector connector;

  @Autowired
  WhatsappHistoryRepository repository;

  @Override
  public void sendNotification(NotificationPayload payload, NotificationHandlerResponse response) {
    try {
      ResponseEntity<WhatsappMessageResponse> responseEntity = connector.sendTextMessage(
          buildWhatsappTextMessageRequest(payload));
      response.addThirdPartyResponse(responseEntity.getBody());
    } catch (
        HttpStatusCodeException e) {
      response.addThirdPartyResponse(
          ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
              .body(e.getResponseBodyAsString()));
      throw new NotificationException(
          String.format("WhatsappHttpConnector failed for request %s", payload));
    }
  }

  @Override
  public void createHistory(BaseNotificationHistoryRequest request) {
    WhatsappHistoryRequest whatsappHistoryRequest = (WhatsappHistoryRequest) request;
    WhatsappHistory whatsappHistory = NotificationHistoryUtility.buildWhatsappHistory(
        whatsappHistoryRequest);
    repository.save(whatsappHistory);
  }

  @Override
  public void updateHistory(BaseNotificationHistoryRequest request) {
    String messageId = request.getMessageId();
    WhatsappHistory whatsappHistory = getWhatsappHistory(messageId);
    repository.save(
        Objects.requireNonNull(ObjectMapperUtility.updateObject(request, whatsappHistory)));
  }

  public WhatsappHistory getWhatsappHistory(String messageId) {
    return repository.findByMessageId(messageId).orElseThrow(
        () -> new NotificationException(CommonConstants.ErrorMessage.NOT_FOUND.getMessage(),
            HttpStatus.NOT_FOUND));
  }

  private WhatsappTextMessageRequest buildWhatsappTextMessageRequest(NotificationPayload payload) {
    WhatsappNotificationPayload whatsappPayload = (WhatsappNotificationPayload) payload;
    WhatsappTextMessageRequest request = new WhatsappTextMessageRequest();
    request.setTo(whatsappPayload.getDestination());
    WhatsappText whatsappText = new WhatsappText();
    whatsappText.setPreviewUrl(false);
    whatsappText.setBody(payload.getMessage());
    request.setText(whatsappText);
    return request;
  }


}
