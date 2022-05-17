package tat.itis.chatsem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tat.itis.chatsem.models.Account;
import tat.itis.chatsem.models.ChatRoom;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatRoom, Long>{
    List<ChatRoom> findAllByParticipantsContaining(Account account);
}
