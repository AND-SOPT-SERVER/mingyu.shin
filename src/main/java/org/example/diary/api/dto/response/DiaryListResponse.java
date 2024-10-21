package org.example.diary.api.dto.response;

import java.util.List;

public class DiaryListResponse {
    private final List<DiaryResponse> diaryResponseList;

    public List<DiaryResponse> getDiaryResponseList() {
        return diaryResponseList;
    }

    public DiaryListResponse(List<DiaryResponse> diaryResponseList) {
        this.diaryResponseList = diaryResponseList;
    }
}
