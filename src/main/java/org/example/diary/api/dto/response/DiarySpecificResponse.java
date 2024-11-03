package org.example.diary.api.dto.response;

import org.example.diary.repository.entity.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DiarySpecificResponse(
        long id,
        String title,
        String body,
        Category category,
        LocalDateTime date
        ) {
}
