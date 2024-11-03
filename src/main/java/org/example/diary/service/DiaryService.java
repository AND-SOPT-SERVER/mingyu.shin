package org.example.diary.service;

import org.example.diary.api.dto.request.DiaryCreateRequest;
import org.example.diary.api.dto.response.DiaryResponse;
import org.example.diary.api.dto.response.DiarySpecificResponse;
import org.example.diary.api.dto.request.DiaryUpdateRequest;
import org.example.diary.repository.adapter.DiaryDeleter;
import org.example.diary.repository.adapter.DiaryFinder;
import org.example.diary.repository.adapter.DiarySaver;
import org.example.diary.repository.adapter.MemberFinder;
import org.example.diary.repository.entity.Category;
import org.example.diary.repository.entity.Diary;
import org.example.diary.repository.entity.Member;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DiaryService {

    private final DiaryFinder diaryFinder;
    private final DiarySaver diarySaver;
    private final DiaryDeleter diaryDeleter;
    private final MemberFinder memberFinder;

    public DiaryService(DiaryFinder diaryFinder, DiarySaver diarySaver, DiaryDeleter diaryDeleter, MemberFinder memberFinder) {
        this.diaryFinder = diaryFinder;
        this.diarySaver = diarySaver;
        this.diaryDeleter = diaryDeleter;
        this.memberFinder = memberFinder;
    }

    public void createDiary(final DiaryCreateRequest diaryCreateRequest) {
        Member member = memberFinder.findMemberById(diaryCreateRequest.userId());
        diarySaver.createDiary(
                Diary.of(
                        diaryCreateRequest.title(),
                        diaryCreateRequest.body(),
                        diaryCreateRequest.category(),
                        LocalDateTime.now(),
                        member
                ));
    }

    public DiarySpecificResponse getDiarySpecific(final long id) {
        Diary diary = diaryFinder.getDiaryEntity(id);
        return new DiarySpecificResponse(
                diary.getId(),
                diary.getTitle(),
                diary.getBody(),
                diary.getCategory(),
                diary.getCreatedAt()
        );
    }

    public List<DiaryResponse> getList() {
        List<Diary> diaryList = diaryFinder.findDiaryList();
        final List<DiaryResponse> diaryListResponseList = new ArrayList<>();
        for (Diary diary : diaryList) {
            diaryListResponseList.add(DiaryResponse.from(diary));
        }
        return diaryListResponseList;
    }

    public List<DiaryResponse> getListByCategory(final Category category) {
        List<Diary> diaryListByCategory = diaryFinder.findDiaryListByCategory(category);
        final List<DiaryResponse> diaryListResponseList = new ArrayList<>();
        for (Diary diary : diaryListByCategory) {
            diaryListResponseList.add(DiaryResponse.from(diary));
        }
        return diaryListResponseList;
    }

    public void updateDiary(final long id, final DiaryUpdateRequest request) {
        diarySaver.updateDiary(id, request.body());
    }

    public void deleteDiary(final long id) {
        diaryDeleter.deleteDiary(id);
    }
}