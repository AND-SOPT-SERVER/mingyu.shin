package org.example.diary.api;

import org.example.diary.repository.Category;

import java.time.LocalDateTime;

public record DiarySpecificResponse(
        long id,
        String title,
        String body,
        Category category,
        LocalDateTime date
        ) {
}
