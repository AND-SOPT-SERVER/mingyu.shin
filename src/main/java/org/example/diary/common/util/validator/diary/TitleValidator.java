package org.example.diary.common.util.validator.diary;

import org.springframework.stereotype.Component;

@Component
public class TitleValidator {

    protected void validate(final String title){
        if(title.isEmpty()){
            throw new RuntimeException("제목이 입력되지 않았습니다.");
        }
        if(title.length() > 10){
            throw new RuntimeException("제목이 최대 글자수를 초과했습니다.(10자)");
        }
    }
}
