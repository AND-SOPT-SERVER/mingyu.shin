package org.example.diary.service;

import org.example.diary.repository.DiaryEntity;
import org.example.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public void createDiary(final String title,final String body) {
        diaryRepository.save(DiaryEntity.of(title,body, LocalDateTime.now()));
    }

    public List<Diary> getList() {
        final List<DiaryEntity> diaryEntityList = diaryRepository.findTop10ByOrderByDateAsc();
        final List<Diary> diaryList = new ArrayList<>();
        for (DiaryEntity diaryEntity : diaryEntityList) {
            diaryList.add(new Diary(diaryEntity.getId(), diaryEntity.getTitle()));
        }
        return diaryList;
    }

    public Diary getDiarySpecific(long id){

    }
}
