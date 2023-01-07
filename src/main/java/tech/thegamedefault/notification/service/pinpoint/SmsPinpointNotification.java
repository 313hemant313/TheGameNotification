package tech.thegamedefault.notification.service.pinpoint;

import tech.thegamedefault.notification.exception.NotificationException;
import tech.thegamedefault.notification.model.NotificationHandlerResponse;
import tech.thegamedefault.notification.model.NotificationPayload;
import tech.thegamedefault.notification.model.SmsNotificationPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.pinpoint.model.ChannelType;
import software.amazon.awssdk.services.pinpoint.model.DirectMessageConfiguration;
import software.amazon.awssdk.services.pinpoint.model.PinpointException;
import software.amazon.awssdk.services.pinpoint.model.SMSMessage;
import software.amazon.awssdk.services.pinpoint.model.SendMessagesResponse;

@Slf4j
@Service
public class SmsPinpointNotification extends BasePinpointNotification {

  public static final String MESSAGE_TYPE = "TRANSACTIONAL";
  public static final String REGISTERED_KEYWORD = "registeredKeyword";
  public static final String SENDER_ID = "senderId";

  @Override
  protected ChannelType getChannelType() {
    return ChannelType.SMS;
  }

  @Override
  public void sendNotification(NotificationPayload request, NotificationHandlerResponse response)
      throws NotificationException {
    SmsNotificationPayload smsNotificationRequest = (SmsNotificationPayload) request;

    try {
      SMSMessage smsMessage = SMSMessage.builder()
          .body(smsNotificationRequest.getMessage())
          .messageType(MESSAGE_TYPE)
//          .originationNumber(smsNotificationRequest.getSource())
          .senderId(SENDER_ID)
          .keyword(REGISTERED_KEYWORD)
          .build();

      DirectMessageConfiguration directMessageConfiguration = DirectMessageConfiguration.builder()
          .smsMessage(smsMessage)
          .build();

      SendMessagesResponse sendMessagesResponse = super.sendMessage(request,
          directMessageConfiguration);

      super.postRequestOperation(sendMessagesResponse, response);

    } catch (PinpointException e) {
      throw new NotificationException(
          String.format("%s: failed due to error: %s", this.getClass().getSimpleName(),
              e.awsErrorDetails().errorMessage()));
    }

  }
}
