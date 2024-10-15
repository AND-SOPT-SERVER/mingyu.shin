package org.example.diary.service;

import org.example.diary.api.DiaryUpdateRequest;
import org.example.diary.repository.DiaryEntity;
import org.example.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        final List<DiaryEntity> diaryEntityList = diaryRepository.findTop10ByCreatedDate();
        final List<Diary> diaryList = new ArrayList<>();
        for (DiaryEntity diaryEntity : diaryEntityList) {
            diaryList.add(new Diary(diaryEntity.getId(), diaryEntity.getTitle()));
        }
        return diaryList;
    }

    public DiaryEntity getDiarySpecific(final long id){
        return getDiaryEntity(id);
    }

    public void updateDiary(final long id,final DiaryUpdateRequest request){
        DiaryEntity diaryEntity = getDiaryEntity(id);
        diaryEntity.setBody(request.body());
        diaryRepository.save(diaryEntity);
    }

    public void deleteDiary(final long id){
        DiaryEntity diaryEntity = getDiaryEntity(id);
        diaryRepository.delete(diaryEntity);
    }

    private DiaryEntity getDiaryEntity(final long id) {
        Optional<DiaryEntity> diaryEntity = diaryRepository.findById(id);
        if(diaryEntity.isEmpty()){
            throw new RuntimeException("존재하지 않은 아이디");
        }
        return diaryEntity.get();
    }
}
