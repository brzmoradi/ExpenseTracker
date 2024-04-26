package com.behrooz.expensetracker.exception.provider;

import com.behrooz.expensetracker.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
@RequiredArgsConstructor
public class ControllerAdvisor {

    private final MessageHelper messageHelper;


}
