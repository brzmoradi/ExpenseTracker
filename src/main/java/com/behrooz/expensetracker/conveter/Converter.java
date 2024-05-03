package com.behrooz.expensetracker.conveter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.behrooz.expensetracker.dto.PageableResponse;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;

public interface Converter<E, D> {
  D convert(E entity);

  default PageableResponse<D> convert(Page<E> page){
    PageableResponse pageableResponse = new PageableResponse<D>();
    pageableResponse.setData(convert(page.getContent()));
    pageableResponse.setTotalPages(page.getTotalPages());
    pageableResponse.setTotalItems(page.getTotalElements());
    return pageableResponse;
  }
  default List<D> convert(List<E> entities) {
    if (CollectionUtils.isEmpty(entities)) return Collections.emptyList();
    return entities.stream().map(this::convert).collect(Collectors.toList());
  }
}
