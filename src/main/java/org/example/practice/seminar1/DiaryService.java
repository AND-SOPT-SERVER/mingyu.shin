package org.example.practice.seminar1;

import java.util.List;

public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();

    public void createDiary(final String body) {
        try {
            Diary diary = Diary.of(null, body);
            diaryRepository.save(diary);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public List<Diary> getDiaryList() {
        return diaryRepository.findAll();
    }

    public void updateDiary(final long id, final String body) {
        try {
            diaryRepository.updateDiaryById(id, body);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void deleteDiary(final long id) {
        try {
            diaryRepository.deleteDiaryById(id);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}