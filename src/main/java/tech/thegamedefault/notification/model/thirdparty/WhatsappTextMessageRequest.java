package tech.thegamedefault.notification.model.thirdparty;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WhatsappTextMessageRequest {

  @JsonProperty("messaging_product")
  String messagingProduct = "whatsapp";
  @JsonProperty("recipient_type")
  String recipientType = "individual";
  String to;
  String type = "text";
  WhatsappText text;

}
