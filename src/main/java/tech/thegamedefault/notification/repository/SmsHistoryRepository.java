package tech.thegamedefault.notification.repository;

import tech.thegamedefault.notification.entity.SmsHistory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsHistoryRepository extends PagingAndSortingRepository<SmsHistory, UUID> {

  List<SmsHistory> findAll();

  Optional<SmsHistory> findByMessageId(String messageId);

}
