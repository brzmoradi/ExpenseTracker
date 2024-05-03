package com.behrooz.expensetracker.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public class Auditable<ID extends Serializable, UID> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private ID id;

  @CreatedBy protected UID userId;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  protected LocalDateTime createdDate;
}
