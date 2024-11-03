package org.example.diary.common.util.validator.diary;

import org.springframework.stereotype.Component;

@Component
public class BodyValidator {
    protected void validate(final String title){
        if(title.isEmpty()){
            throw new RuntimeException("본문이 입력되지 않았습니다.");
        }
        if(title.length() > 30){
            throw new RuntimeException("본문이 최대 글자수를 초과했습니다.(30자)");
        }
    }
}
