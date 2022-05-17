package tat.itis.chatsem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tat.itis.chatsem.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
