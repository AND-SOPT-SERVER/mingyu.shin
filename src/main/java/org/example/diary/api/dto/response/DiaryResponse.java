package org.example.diary.api.dto.response;

import org.example.diary.repository.entity.DiaryEntity;

import java.time.LocalDateTime;

public class DiaryResponse {
    private final long id;
    private final String title;
    private final String username;
    private final LocalDateTime createdAt;


    private DiaryResponse(long id, String title,LocalDateTime createdAt, String username) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.username = username;
    }

    public static DiaryResponse from(final DiaryEntity diary){
        return new DiaryResponse(diary.getId(), diary.getTitle(), diary.getCreatedAt(),diary.getMember().getUserName());
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername(){
        return username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
