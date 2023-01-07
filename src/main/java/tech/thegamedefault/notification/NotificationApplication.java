package tech.thegamedefault.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableKafka
@EnableWebMvc
@EnableAsync
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EntityScan(basePackages = {"tech.thegamedefault.notification"})
@EnableJpaRepositories({"tech.thegamedefault.notification"})
@SpringBootApplication(scanBasePackages = {"tech.thegamedefault.notification"})
public class NotificationApplication {

  public static void main(String[] args) {
    SpringApplication.run(NotificationApplication.class, args);
  }

}
