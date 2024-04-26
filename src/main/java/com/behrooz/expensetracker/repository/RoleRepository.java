package com.behrooz.expensetracker.repository;

import com.behrooz.expensetracker.entity.Role;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {

  @Query("SELECT a.id FROM Role a")
  Set<Integer> findAllIds();
}
