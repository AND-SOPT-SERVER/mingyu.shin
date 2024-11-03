package org.example.diary.repository.adapter;

import org.example.diary.repository.entity.Diary;
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
        Diary diary = diaryFinder.getDiaryEntity(id);
        diaryRepository.delete(diary);
    }
}

