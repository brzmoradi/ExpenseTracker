package com.behrooz.expensetracker.repository;

import com.behrooz.expensetracker.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long>, GetWithUserSecurity<Tag> {

    List<Tag> findAllByUserIdAndIdIn(Integer userId , List<Long> ids);
}
