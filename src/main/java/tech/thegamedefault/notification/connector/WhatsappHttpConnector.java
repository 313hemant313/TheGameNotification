package tech.thegamedefault.notification.connector;

import tech.thegamedefault.notification.config.WhatsappClientConfig;
import tech.thegamedefault.notification.model.thirdparty.WhatsappTextMessageRequest;
import tech.thegamedefault.notification.model.thirdparty.response.WhatsappErrorResponse;
import tech.thegamedefault.notification.model.thirdparty.response.WhatsappMessageResponse;
import tech.thegamedefault.notification.utility.GenericRestClient;
import tech.thegamedefault.notification.utility.ObjectMapperUtility;
import java.util.Objects;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Configuration
public class WhatsappHttpConnector extends WhatsappClientConfig {

  public ResponseEntity<WhatsappMessageResponse> sendTextMessage(
      WhatsappTextMessageRequest request) {
    ResponseEntity<WhatsappMessageResponse> responseEntity = null;
    GenericRestClient<WhatsappTextMessageRequest, WhatsappMessageResponse> restClient = new GenericRestClient<>();
    responseEntity = restClient.execute(getUrl(), HttpMethod.POST,
        restClient.getBearerTokenHttpHeaders(getAccessToken()), request,
        WhatsappMessageResponse.class);
    return responseEntity;

  }

  private WhatsappErrorResponse getErrorResponse(
      ResponseEntity<WhatsappMessageResponse> responseEntity) {
    if (Objects.isNull(responseEntity)) {
      return null;
    }
    if (HttpStatus.OK != responseEntity.getStatusCode()) {
      return ObjectMapperUtility.convertValue(responseEntity.getBody(),
          WhatsappErrorResponse.class);
    }
    return null;
  }

}
