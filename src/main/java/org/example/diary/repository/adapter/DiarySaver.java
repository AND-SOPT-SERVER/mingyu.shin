package org.example.diary.repository.adapter;

import org.example.diary.repository.entity.Diary;
import org.example.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DiarySaver {

    private final DiaryRepository diaryRepository;
    private final DiaryFinder diaryFinder;

    public DiarySaver (final DiaryRepository diaryRepository,final DiaryFinder diaryFinder) {
        this.diaryRepository = diaryRepository;
        this.diaryFinder = diaryFinder;
    }

    public void createDiary(final Diary diary){
        Optional<Diary> diaryEntity = diaryFinder.findByTitle(diary.getTitle());
        if (diaryEntity.isPresent()) {
            throw new RuntimeException("중복되는 제목입니다.");
        }
        diaryRepository.save(diary);
    }

    public void updateDiary(final long id, final String body){
        Diary diary = diaryFinder.getDiaryEntity(id);
        diary.setBody(body);
        diaryRepository.save(diary);
    }
}
