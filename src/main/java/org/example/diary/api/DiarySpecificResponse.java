package org.example.diary.api;

import java.time.LocalDateTime;

public record DiarySpecificResponse(
        long id,
        String title,
        String body,
        LocalDateTime date
        ) {
}
