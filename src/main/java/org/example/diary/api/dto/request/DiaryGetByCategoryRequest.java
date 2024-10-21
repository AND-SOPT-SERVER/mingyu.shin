package org.example.diary.api.dto.request;

import org.example.diary.repository.entity.Category;

public record DiaryGetByCategoryRequest(
        Category category
) {
}