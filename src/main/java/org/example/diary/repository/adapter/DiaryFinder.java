package org.example.diary.repository.adapter;

import org.example.diary.repository.entity.Category;
import org.example.diary.repository.entity.Diary;
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

    public List<Diary> findDiaryList() {
        return listPaging(diaryRepository.findAllOrderByBodyLengthAsc());
    }

    public List<Diary> findDiaryListByCategory(final Category category) {
        return listPaging(diaryRepository.findAllByCategoryOrderByBodyLengthDesc(category));
    }

    public Diary getDiaryEntity(final long id) {
        Optional<Diary> diaryEntity = diaryRepository.findById(id);
        if (diaryEntity.isEmpty()) {
            throw new RuntimeException("존재하지 않은 아이디");
        }
        return diaryEntity.get();
    }

    public Optional<Diary> findByTitle(final String title) {
        return diaryRepository.findByTitle(title);
    }

    private List<Diary> listPaging(final List<Diary> diaryList) {
        final List<Diary> diaryListResponseList = new ArrayList<>();
        for (Diary diary : diaryList) {
            int diaryLength = diaryListResponseList.size();
            if (
                    diaryLength < 10 ||
                            diaryListResponseList.get(diaryLength - 1).getBody().length() == diary.getBody().length()
            ) {
                diaryListResponseList.add(diary);
            }
        }
        return diaryList;
    }
}
