package com.behrooz.expensetracker.controller.tag;

import com.behrooz.expensetracker.conveter.TagConverter;
import com.behrooz.expensetracker.dto.PageableResponse;
import com.behrooz.expensetracker.dto.tag.TagRegisterRequest;
import com.behrooz.expensetracker.dto.tag.TagResponse;
import com.behrooz.expensetracker.dto.tag.TagUpdateRequest;
import com.behrooz.expensetracker.entity.Tag;
import com.behrooz.expensetracker.service.TagService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class TagController {

  private final TagService tagService;
  private final TagConverter converter = new TagConverter();

  @GetMapping("${apis.secure}/tags/{id}")
  public TagResponse get(@PathVariable @NotNull Long id) {
    Tag tag = tagService.get(id);
    return converter.convert(tag);
  }

  @PostMapping("${apis.secure}/tags")
  public TagResponse save(@RequestBody @Valid TagRegisterRequest request) {
    Tag persistedTag = tagService.save(request);
    return converter.convert(persistedTag);
  }

  @PutMapping("${apis.secure}/tags/{id}")
  public TagResponse update(@PathVariable Long id, @RequestBody TagUpdateRequest request) {
    Tag updatedtag = tagService.update(id, request);
    return converter.convert(updatedtag);
  }

  @GetMapping("${apis.secure}/tags")
  public PageableResponse<TagResponse> getAll(
      @RequestParam(defaultValue = "0") @Min(value = 0, message = "error.validation.min")
          Integer pageIndex,
      @RequestParam(defaultValue = "10")
          @Min(value = 1, message = "error.validation.min")
          @Max(value = 100, message = "error.validation.max")
          Integer pageSize,
      @RequestParam(defaultValue = "degree", required = false) String sortBy) {
    Pageable paging = PageRequest.of(pageIndex, pageSize, Sort.by(sortBy).descending());
    Page<Tag> tags = tagService.get(paging);
    return converter.convert(tags);
  }
}
