package org.example.diary.repository.adapter;

import org.example.diary.repository.entity.Category;
import org.example.diary.repository.entity.DiaryEntity;
import org.example.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DiaryFinder {
    private final DiaryRepository diaryRepository;

    public DiaryFinder(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public List<DiaryEntity> findDiaryList() {
        return listPaging(diaryRepository.findAllOrderByBodyLengthAsc());
    }

    public List<DiaryEntity> findDiaryListByCategory(final Category category) {
        return listPaging(diaryRepository.findAllByCategoryOrderByBodyLengthDesc(category));
    }

    public DiaryEntity getDiaryEntity(final long id) {
        Optional<DiaryEntity> diaryEntity = diaryRepository.findById(id);
        if (diaryEntity.isEmpty()) {
            throw new RuntimeException("존재하지 않은 아이디");
        }
        return diaryEntity.get();
    }

    public Optional<DiaryEntity> findByTitle(final String title) {
        return diaryRepository.findByTitle(title);
    }

    private List<DiaryEntity> listPaging(final List<DiaryEntity> diaryEntityList) {
        final List<DiaryEntity> diaryListResponseList = new ArrayList<>();
        for (DiaryEntity diaryEntity : diaryEntityList) {
            int diaryLength = diaryListResponseList.size();
            if (
                    diaryLength < 10 ||
                            diaryListResponseList.get(diaryLength - 1).getBody().length() == diaryEntity.getBody().length()
            ) {
                diaryListResponseList.add(diaryEntity);
            }
        }
        return diaryEntityList;
    }
}
