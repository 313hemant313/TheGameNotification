package tech.thegamedefault.notification.consumer;

import tech.thegamedefault.notification.exception.NotificationException;
import tech.thegamedefault.notification.framework.NotificationHandlerFactory;
import tech.thegamedefault.notification.model.NotificationHandlerResponse;
import tech.thegamedefault.notification.model.NotificationPayload;
import tech.thegamedefault.notification.utility.CommonConstants.Status;
import tech.thegamedefault.notification.utility.ObjectMapperUtility;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaTopicConsumer {

  @KafkaListener(topics = {"#{'${app.kafka.consumer.topic}'.split(',')}"})
  public void receive(ConsumerRecord<String, String> consumerRecord) {
    NotificationPayload basePayload = ObjectMapperUtility.readValue(consumerRecord.value(),
        NotificationPayload.class);

    if (Objects.isNull(basePayload) || Objects.isNull(basePayload.getNotificationType())) {
      throw new NotificationException("[ValidationError] Notification type is mandatory");
    }

    NotificationPayload payload = NotificationHandlerFactory.getPayload(consumerRecord.value(),
        basePayload.getNotificationType());

    NotificationHandlerResponse response = NotificationHandlerFactory.getHandler(basePayload)
        .dispatch(payload);

    if (Status.FAILED == response.getStatus() || Status.VALIDATION_FAILED == response.getStatus()) {
      throw new NotificationException(
          String.format("Failed to dispatch notification for request: %s", payload));
    }

  }

  @KafkaListener(topics = {"#{'${app.kafka.consumer.topic.dlt}'.split(',')}"})
  public void listenDlt(ConsumerRecord<String, String> consumerRecord,
      @Header(KafkaHeaders.DLT_EXCEPTION_STACKTRACE) String exceptionStackTrace) {
    log.error(exceptionStackTrace);
  }

}
