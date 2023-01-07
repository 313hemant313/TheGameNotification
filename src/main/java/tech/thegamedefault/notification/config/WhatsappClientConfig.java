package tech.thegamedefault.notification.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "facebook.whatsapp")
public class WhatsappClientConfig {

  String url;
  String phoneNumberId;
  String accountId;
  String accessToken;

}
