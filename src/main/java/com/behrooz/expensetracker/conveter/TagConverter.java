package com.behrooz.expensetracker.conveter;

import com.behrooz.expensetracker.dto.tag.TagResponse;
import com.behrooz.expensetracker.entity.Tag;
import com.behrooz.expensetracker.util.DateUtil;

public class TagConverter implements Converter<Tag, TagResponse> {
  @Override
  public TagResponse convert(Tag entity) {
    TagResponse tagResponse = new TagResponse();
    tagResponse.setId(entity.getId());
    tagResponse.setName(entity.getName());
    tagResponse.setDegree(entity.getDegree());
    tagResponse.setCreatedDate(DateUtil.format(entity.getCreatedDate()));
    return tagResponse;
  }
}
