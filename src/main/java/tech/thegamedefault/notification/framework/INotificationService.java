package tech.thegamedefault.notification.framework;

import tech.thegamedefault.notification.model.NotificationHandlerResponse;
import tech.thegamedefault.notification.model.NotificationPayload;
import tech.thegamedefault.notification.model.history.BaseNotificationHistoryRequest;
import tech.thegamedefault.notification.utility.CommonConstants.Status;

public interface INotificationService {

  default NotificationHandlerResponse notify(NotificationPayload request) {
    NotificationHandlerResponse response = new NotificationHandlerResponse();
    try {
      sendNotification(request, response);
      if (Status.FAILED_AT_THIRD_PARTY != response.getStatus()) {
        response.setStatus(Status.SUCCESS);
      }
    } catch (Exception e) {
      response.setStatus(Status.FAILED);
      response.setErrorMessage(
          String.format("%s failed with error: %s", this.getClass().getSimpleName(),
              e.getMessage()));
    }
    return response;
  }

  void sendNotification(NotificationPayload request, NotificationHandlerResponse response);

  void createHistory(BaseNotificationHistoryRequest request);

  void updateHistory(BaseNotificationHistoryRequest request);

}
