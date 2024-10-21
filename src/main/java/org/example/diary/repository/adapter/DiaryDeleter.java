package org.example.diary.repository.adapter;

import org.example.diary.repository.entity.DiaryEntity;
import org.example.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;

@Component
public class DiaryDeleter {
    private final DiaryRepository diaryRepository;
    private final DiaryFinder diaryFinder;

    public DiaryDeleter (DiaryRepository diaryRepository,DiaryFinder diaryFinder) {

        this.diaryRepository = diaryRepository;
        this.diaryFinder = diaryFinder;
    }

    public void deleteDiary(final long id){
        DiaryEntity diaryEntity = diaryFinder.getDiaryEntity(id);
        diaryRepository.delete(diaryEntity);
    }
}

