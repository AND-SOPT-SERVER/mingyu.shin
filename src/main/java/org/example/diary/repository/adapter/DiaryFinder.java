package org.example.diary.repository.adapter;

import org.example.diary.repository.entity.Category;
import org.example.diary.repository.entity.DiaryEntity;
import org.example.diary.repository.DiaryRepository;
import org.example.diary.service.Diary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DiaryFinder {
    private final DiaryRepository diaryRepository;

    public DiaryFinder (DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public List<Diary> findDiaryList(){
        final List<DiaryEntity> diaryEntityList = diaryRepository.findAllOrderByBodyLengthDesc();
        final List<Diary> diaryList = new ArrayList<>();
        for (DiaryEntity diaryEntity : diaryEntityList) {
            int diaryLength = diaryList.size();
            if (
                    diaryLength < 10 ||
                            diaryList.get(diaryLength - 1).getBody().length() == diaryEntity.getBody().length()
            ) {
                diaryList.add(new Diary(diaryEntity.getId(), diaryEntity.getTitle(), diaryEntity.getBody()));
            }
        }
        return diaryList;
    }

    public List<DiaryEntity> findAllByCategory(Category category){
        return diaryRepository.findAllByCategory(category);
    }

    public DiaryEntity getDiaryEntity(final long id){
        Optional<DiaryEntity> diaryEntity = diaryRepository.findById(id);
        if (diaryEntity.isEmpty()) {
            throw new RuntimeException("존재하지 않은 아이디");
        }
        return diaryEntity.get();
    }

    public Optional<DiaryEntity> findByTitle(String title){
        return diaryRepository.findByTitle(title);
    }
}
