package tech.thegamedefault.notification.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.thegamedefault.notification.utility.ObjectMapperUtility;

@RestController
public class NotificationController {

  @Autowired
  private KafkaTemplate kafkaTemplate;

  @PostMapping("/notification/send")
  public ResponseEntity<Object> sendNotification(@RequestBody Map<String, Object> payload) {
    try {
      kafkaTemplate.send("Notification", "NotificationKey",
          ObjectMapperUtility.writeValueAsString(payload));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return ResponseEntity.ok().build();
  }

}
