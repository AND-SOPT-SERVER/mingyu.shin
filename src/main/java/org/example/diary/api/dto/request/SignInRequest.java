package org.example.diary.api.dto.request;

public record SignInRequest(
        String username,
        String password
) {
}
