package tech.thegamedefault.notification.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
@Table(indexes = {
    @Index(name = "index_message_id", columnList = "messageId", unique = true),
    @Index(name = "index_status", columnList = "status")
})
public class EmailHistory extends BaseNotificationHistoryEntity {

  private String subject;

  @Column(columnDefinition = "TEXT")
  private String htmlBody;

  private String templateName;

}
