package tech.thegamedefault.notification.model.history;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class SmsHistoryRequest extends BaseNotificationHistoryRequest {

  String source;
  List<String> destinations = new ArrayList<>();
  String templateName;

}
