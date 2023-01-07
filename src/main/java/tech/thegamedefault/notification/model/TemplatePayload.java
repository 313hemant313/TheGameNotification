package tech.thegamedefault.notification.model;

import java.util.Map;
import lombok.Data;

@Data
public class TemplatePayload {

  String templateName;
  Map<String, Object> data;

}
