package tat.itis.chatsem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tat.itis.chatsem.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
