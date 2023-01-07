package tech.thegamedefault.notification.repository;

import tech.thegamedefault.notification.entity.WhatsappHistory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhatsappHistoryRepository extends
    PagingAndSortingRepository<WhatsappHistory, UUID> {

  List<WhatsappHistory> findAll();

  Optional<WhatsappHistory> findByMessageId(String messageId);

}
