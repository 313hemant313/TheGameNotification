package tech.thegamedefault.notification.model.thirdparty.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorMessage {

  String message;
  String type;
  String code;
  @JsonProperty("error_subcode")
  String errorSubCode;
  @JsonProperty("fbtrace_id")
  String fbTraceId;


}
