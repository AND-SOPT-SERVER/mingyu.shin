package org.example.diary.api;

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
        diaryService.createDiary(request.title(), request.body());
    }

    @GetMapping("/diaries")
    ResponseEntity<DiaryListResponse> get() {
        List<Diary> diaryList = diaryService.getList();
        List<DiaryResponse> diaryResponseList = new ArrayList<>();
        for (Diary diary : diaryList) {
            diaryResponseList.add(new DiaryResponse(diary.getId(), diary.getName()));
        }
        return ResponseEntity.ok(new DiaryListResponse(diaryResponseList));
    }

    @GetMapping("/diaries/{id}")
    ResponseEntity<DiarySpecificResponse> getSpecific(@PathVariable long id) {
        DiaryEntity diaryEntity = diaryService.getDiarySpecific(id);
        return ResponseEntity.ok(new DiarySpecificResponse(
                diaryEntity.getId(),
                diaryEntity.getTitle(),
                diaryEntity.getBody(),
                diaryEntity.getDate()
        ));
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
