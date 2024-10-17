package org.example.diary.api;

import org.example.diary.repository.Category;
import org.example.diary.repository.DiaryEntity;
import org.example.diary.service.Diary;
import org.example.diary.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/diaries")
    void post(@RequestBody DiaryCreateRequest request) {
        diaryService.createDiary(request);
    }

    @GetMapping("/diaries")
    ResponseEntity<DiaryListResponse> get() {
        List<Diary> diaryList = diaryService.getListById();
        List<DiaryResponse> diaryResponseList = new ArrayList<>();
        for (Diary diary : diaryList) {
            diaryResponseList.add(new DiaryResponse(diary.getId(), diary.getTitle()));
        }
        return ResponseEntity.ok(new DiaryListResponse(diaryResponseList));
    }

    @GetMapping("/diaries/category")
    ResponseEntity<DiaryListResponse> getByCategory(@RequestBody Category category){
        List<DiaryEntity> diaryList = diaryService.getListByCategory(category);
        List<DiaryResponse> diaryResponseList = new ArrayList<>();
        for (DiaryEntity diary : diaryList) {
            diaryResponseList.add(new DiaryResponse(diary.getId(), diary.getTitle()));
        }
        return ResponseEntity.ok(new DiaryListResponse(diaryResponseList));
    }

    @GetMapping("/diaries/{id}")
    ResponseEntity<DiarySpecificResponse> getSpecific(@PathVariable long id) {
        DiarySpecificResponse response =  diaryService.getDiarySpecific(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/diaries/{id}")
    void updateDiary(@PathVariable final long id,@RequestBody final DiaryUpdateRequest request) {
        diaryService.updateDiary(id, request);
    }

    @DeleteMapping("/diaries/{id}")
    void deleteDiary(@PathVariable final long id){
        diaryService.deleteDiary(id);
    }
}
