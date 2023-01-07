package tech.thegamedefault.notification.utility;

import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GenericRestClient<T, V> {

  private final RestTemplate restTemplate;

  public GenericRestClient() {
    restTemplate = new RestTemplateBuilder()
        .setConnectTimeout(Duration.ofSeconds(20))
        .setReadTimeout(Duration.ofSeconds(20))
        .build();
  }

  public ResponseEntity<V> execute(String url, HttpMethod requestType, HttpHeaders headers, T data,
      Class<V> genericClass) {
    HttpEntity<T> entity = new HttpEntity<>(data, headers);
    return restTemplate.exchange(url, requestType,
        entity, genericClass);
  }

  public HttpHeaders getDefaultHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
    return headers;
  }

  public HttpHeaders getBearerTokenHttpHeaders(String token) {
    HttpHeaders headers = getDefaultHttpHeaders();
    headers.set(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token));
    return headers;
  }

}
