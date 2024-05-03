package com.behrooz.expensetracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Setter
@Getter
@Audited
@AuditOverrides({
        @AuditOverride(forClass = Auditable.class, name = "id"),
        @AuditOverride(forClass = Auditable.class, name = "userId")
})
public class Budget extends Auditable<Long, Integer> {

  @Audited(targetAuditMode = NOT_AUDITED)
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id")
  private Category category;

  private Long amount;
  private Long thresholdAmount;
  private LocalDate startDate;
  private LocalDate endDate;
}
