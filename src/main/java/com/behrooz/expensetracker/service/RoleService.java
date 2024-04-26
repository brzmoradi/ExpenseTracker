package com.behrooz.expensetracker.service;

import com.behrooz.expensetracker.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
  List<Role> get(Set<Integer> roleIds);
}
