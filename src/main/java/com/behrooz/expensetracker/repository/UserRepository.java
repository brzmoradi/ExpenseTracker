package com.behrooz.expensetracker.repository;

import com.behrooz.expensetracker.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByUsername(String username);

  Optional<User> findByUsernameAndActiveTrue(String username);

  Boolean existsByUsername(String username);
}
