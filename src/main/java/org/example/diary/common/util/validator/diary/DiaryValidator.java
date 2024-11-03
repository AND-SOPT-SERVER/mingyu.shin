package org.example.diary.common.util.validator.diary;

import org.springframework.stereotype.Component;

@Component
public class DiaryValidator {

    private final BodyValidator bodyValidator;

    private final TitleValidator titleValidator;

    public DiaryValidator(BodyValidator bodyValidator, TitleValidator titleValidator) {
        this.bodyValidator = bodyValidator;
        this.titleValidator = titleValidator;
    }

    public void validate(final String title, final String body) {
        titleValidator.validate(title);
        bodyValidator.validate(title);
    }
}
