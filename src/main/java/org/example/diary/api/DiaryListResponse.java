package org.example.diary.api;

import java.util.List;

public class DiaryListResponse {
    private List<DiaryResponse> diaryResponseList;

    public List<DiaryResponse> getDiaryResponseList() {
        return diaryResponseList;
    }

    public DiaryListResponse(List<DiaryResponse> diaryResponseList) {
        this.diaryResponseList = diaryResponseList;
    }
}
