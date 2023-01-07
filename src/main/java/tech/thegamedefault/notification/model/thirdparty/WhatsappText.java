package tech.thegamedefault.notification.model.thirdparty;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WhatsappText {

  @JsonProperty("preview_url")
  boolean previewUrl;
  String body;

}
