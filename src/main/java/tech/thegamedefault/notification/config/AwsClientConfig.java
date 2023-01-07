package tech.thegamedefault.notification.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "aws.pinpoint")
public class AwsClientConfig {

  String accessKey;
  String secret;
  String region;

  String projectId;

}
