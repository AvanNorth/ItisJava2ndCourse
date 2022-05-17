package tat.itis.chatsem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tat.itis.chatsem.models.Account;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}
