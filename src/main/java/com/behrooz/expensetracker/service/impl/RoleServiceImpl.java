package com.behrooz.expensetracker.service.impl;

import com.behrooz.expensetracker.entity.Role;
import com.behrooz.expensetracker.exception.RoleNotFoundException;
import com.behrooz.expensetracker.repository.RoleRepository;
import com.behrooz.expensetracker.service.RoleService;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;

  @Override
  public List<Role> get(Set<Integer> roleIds) {
    if (CollectionUtils.isEmpty(roleIds)) return Collections.emptyList();
    List<Role> roles = roleRepository.findAllById(roleIds);

    if (roles.size() != roleIds.size()) {
      throw new RoleNotFoundException();
    }
    return roles;
  }
}
