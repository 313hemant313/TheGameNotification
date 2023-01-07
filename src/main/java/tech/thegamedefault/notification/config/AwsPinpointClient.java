package tech.thegamedefault.notification.config;


import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.pinpoint.PinpointClient;

@Configuration
public class AwsPinpointClient extends AwsClientConfig {

  public PinpointClient getPinpointClient() {
    return PinpointClient.builder()
        .region(Region.of(region))
        .credentialsProvider(
            StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secret)))
        .build();
  }

}
