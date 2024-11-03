package org.example.diary.api.dto.request;

import org.example.diary.repository.entity.Category;

public record DiaryCreateRequest(
        String title,
        String body,
        Category category,
        long userId
){
}
