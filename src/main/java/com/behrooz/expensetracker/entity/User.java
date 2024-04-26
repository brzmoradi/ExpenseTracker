package com.behrooz.expensetracker.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.util.CollectionUtils;

@Getter
@Setter
@Entity
@Table(name = "user_")
@SQLDelete(sql = "update user_ set deleted = 1 where id = ?")
@Where(clause = "deleted = false")
public class User extends Auditable<Integer> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String title;

  @Column(unique = true)
  private String username;

  private String password;
  private boolean active;
  private LocalDateTime lastLoginDate;
  private boolean deleted;

  @ManyToMany(
      cascade = {CascadeType.DETACH},
      fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<Role> roles;

  @Transient private String savedPassword;
@Transient
  public List<String> getRolesListString() {
    if (CollectionUtils.isEmpty(this.roles)) return Collections.emptyList();
    return this.roles.stream().map(Role::getName).collect(Collectors.toList());
  }
}
