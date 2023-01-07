package tech.thegamedefault.notification.model;

import tech.thegamedefault.notification.utility.CommonConstants.Status;
import tech.thegamedefault.notification.utility.ObjectMapperUtility;
import lombok.Data;

@Data
public class NotificationHandlerResponse {

  String messageId;
  Status status;
  String errorMessage;
  String thirdPartyResponse;

  public void addThirdPartyResponse(Object thirdPartyResponseObj) {
    this.thirdPartyResponse = ObjectMapperUtility.writeValueAsString(thirdPartyResponseObj);
    if ("{}".equalsIgnoreCase(this.thirdPartyResponse)) {
      this.thirdPartyResponse = thirdPartyResponseObj.toString();
    }
  }

}
