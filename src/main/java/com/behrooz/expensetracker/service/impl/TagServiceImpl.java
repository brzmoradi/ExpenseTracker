package com.behrooz.expensetracker.service.impl;

import com.behrooz.expensetracker.config.security.OnlineUser;
import com.behrooz.expensetracker.dto.tag.TagRegisterRequest;
import com.behrooz.expensetracker.dto.tag.TagUpdateRequest;
import com.behrooz.expensetracker.entity.Tag;
import com.behrooz.expensetracker.exception.InvalidParameterException;
import com.behrooz.expensetracker.exception.TagNotFoundException;
import com.behrooz.expensetracker.repository.TagRepository;
import com.behrooz.expensetracker.service.TagService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

  private final TagRepository tagRepository;

  private final OnlineUser onlineUser;

  @Override
  public Tag save(TagRegisterRequest dto) {
    Tag tag = new Tag();
    tag.setDegree(dto.getDegree());
    tag.setName(dto.getName());
    return tagRepository.save(tag);
  }

  @Override
  public Tag get(Long id) {
    return tagRepository
        .findByIdAndUserId(id, onlineUser.getId())
        .orElseThrow(TagNotFoundException::new);
  }

  @Override
  public Page<Tag> get(Pageable pageable) {
    return tagRepository.findAllByUserId(onlineUser.getId(), pageable);
  }

  @Transactional
  @Override
  public Tag update(Long id, TagUpdateRequest update) {
    if (update == null || (update.getName() == null && update.getDegree() == null))
      throw new InvalidParameterException();
    Tag tag = this.get(id);
    Optional.of(update.getName()).ifPresent(tag::setName);
    Optional.of(update.getDegree()).ifPresent(tag::setDegree);
    return tagRepository.save(tag);
  }

  @Transactional
  @Override
  public void delete(Long id) {
    Tag tag = this.get(id);
    tagRepository.delete(tag);
  }

  @Override
  public List<Tag> get(List<Long> ids) {
    List<Tag> tags = tagRepository.findAllByUserIdAndIdIn(onlineUser.getId(), ids);
    if (tags == null || !(tags.size() == ids.size())) throw new TagNotFoundException();
    return tags;
  }
}
