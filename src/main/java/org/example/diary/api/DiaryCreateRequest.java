package org.example.diary.api;

import org.example.diary.repository.Category;
import org.hibernate.Cache;

public record DiaryCreateRequest(
        String title,
        String body,
        Category category
){
}
