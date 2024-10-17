package org.example.diary.service;

import org.example.diary.api.DiaryCreateRequest;
import org.example.diary.api.DiarySpecificResponse;
import org.example.diary.api.DiaryUpdateRequest;
import org.example.diary.repository.Category;
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

    public void createDiary(final DiaryCreateRequest diaryCreateRequest) {
        Optional<DiaryEntity> diaryEntity = diaryRepository.findByTitle(diaryCreateRequest.title());
        if (diaryEntity.isPresent()) {
            throw new RuntimeException("중복되는 제목입니다.");
        }
        diaryRepository.save(
                DiaryEntity.of(
                        diaryCreateRequest.title(),
                        diaryCreateRequest.body(),
                        diaryCreateRequest.category(),
                        LocalDateTime.now()
                ));
    }

    public DiarySpecificResponse getDiarySpecific(final long id) {
        DiaryEntity diaryEntity = getDiaryEntity(id);
        return new DiarySpecificResponse(
                diaryEntity.getId(),
                diaryEntity.getTitle(),
                diaryEntity.getBody(),
                diaryEntity.getCategory(),
                diaryEntity.getDate()
        );
    }

    public List<Diary> getListById() {
        final List<DiaryEntity> diaryEntityList = diaryRepository.findAllOrderByBodyLengthDesc();
        final List<Diary> diaryList = new ArrayList<>();
        for (DiaryEntity diaryEntity : diaryEntityList) {
            diaryList.add(new Diary(diaryEntity.getId(), diaryEntity.getTitle()));
        }
        return diaryList;
    }

    public List<DiaryEntity> getListByCategory(final Category category){
        return diaryRepository.findAllByCategory(category);
    }

    public void updateDiary(final long id, final DiaryUpdateRequest request) {
        DiaryEntity diaryEntity = getDiaryEntity(id);
        diaryEntity.setBody(request.body());
        diaryRepository.save(diaryEntity);
    }

    public void deleteDiary(final long id) {
        DiaryEntity diaryEntity = getDiaryEntity(id);
        diaryRepository.delete(diaryEntity);
    }

    private DiaryEntity getDiaryEntity(final long id) {
        Optional<DiaryEntity> diaryEntity = diaryRepository.findById(id);
        if (diaryEntity.isEmpty()) {
            throw new RuntimeException("존재하지 않은 아이디");
        }
        return diaryEntity.get();
    }
}
