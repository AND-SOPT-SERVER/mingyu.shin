package org.example.diary.api.dto.request;

public record MemberCreateRequest(
        String userName,
        String password,
        String nickname
) {
}
