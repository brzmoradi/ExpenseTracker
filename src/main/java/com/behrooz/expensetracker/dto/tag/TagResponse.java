package com.behrooz.expensetracker.dto.tag;

import com.behrooz.expensetracker.dto.AbstractResponseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TagResponse extends AbstractResponseDto<Long> {
    private String name;
    private Integer degree;

}
