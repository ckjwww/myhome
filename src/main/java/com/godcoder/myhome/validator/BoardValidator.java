package com.godcoder.myhome.validator;

import com.godcoder.myhome.entity.contentDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return contentDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        contentDTO contentdto = (contentDTO) obj;
        if (StringUtils.isEmpty(contentdto.getContent())) {
            errors.rejectValue("content", "content", "내용을입력하세요.");
        }

    }
}
