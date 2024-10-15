package org.example.diary.api;

public record DiaryCreateRequest(
        String title,
        String body
){
}
