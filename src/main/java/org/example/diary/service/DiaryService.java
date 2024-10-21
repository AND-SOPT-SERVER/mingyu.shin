package org.example.diary.service;

import org.example.diary.api.dto.request.DiaryCreateRequest;
import org.example.diary.api.dto.response.DiarySpecificResponse;
import org.example.diary.api.dto.request.DiaryUpdateRequest;
import org.example.diary.repository.adapter.DiaryDeleter;
import org.example.diary.repository.adapter.DiaryFinder;
import org.example.diary.repository.adapter.DiarySaver;
import org.example.diary.repository.entity.Category;
import org.example.diary.repository.entity.DiaryEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DiaryService {

    private final DiaryFinder diaryFinder;
    private final DiarySaver diarySaver;
    private final DiaryDeleter diaryDeleter;

    public DiaryService(DiaryFinder diaryFinder,DiarySaver diarySaver,DiaryDeleter diaryDeleter) {
        this.diaryFinder = diaryFinder;
        this.diarySaver = diarySaver;
        this.diaryDeleter = diaryDeleter;
    }

    public void createDiary(final DiaryCreateRequest diaryCreateRequest) {
        diarySaver.createDiary(
                DiaryEntity.of(
                        diaryCreateRequest.title(),
                        diaryCreateRequest.body(),
                        diaryCreateRequest.category(),
                        LocalDateTime.now()
                ));
    }

    public DiarySpecificResponse getDiarySpecific(final long id) {
        DiaryEntity diaryEntity = diaryFinder.getDiaryEntity(id);
        return new DiarySpecificResponse(
                diaryEntity.getId(),
                diaryEntity.getTitle(),
                diaryEntity.getBody(),
                diaryEntity.getCategory(),
                diaryEntity.getDate()
        );
    }

    public List<Diary> getList() {
        return diaryFinder.findDiaryList();
    }

    public List<DiaryEntity> getListByCategory(final Category category) {
        return diaryFinder.findAllByCategory(category);
    }

    public void updateDiary(final long id, final DiaryUpdateRequest request) {
        diarySaver.updateDiary(id, request.body());
    }

    public void deleteDiary(final long id) {
        diaryDeleter.deleteDiary(id);
    }
}