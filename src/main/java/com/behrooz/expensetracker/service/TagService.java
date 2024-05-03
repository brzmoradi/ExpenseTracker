package com.behrooz.expensetracker.service;

import com.behrooz.expensetracker.dto.tag.TagRegisterRequest;
import com.behrooz.expensetracker.dto.tag.TagUpdateRequest;
import com.behrooz.expensetracker.entity.Tag;

import java.util.List;

public interface TagService extends GetAndUpdateService<Tag, TagRegisterRequest, TagUpdateRequest> {
  void delete(Long id);

  List<Tag> get(List<Long> ids);
}
