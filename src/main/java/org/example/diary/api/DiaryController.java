package org.example.diary.api;

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

    @PostMapping("/post")
    void post(final String title, final String body) {
        diaryService.createDiary(title, body);
    }

    @GetMapping("/post")
    ResponseEntity<DiaryListResponse> get() {
        List<Diary> diaryList = diaryService.getList();
        List<DiaryResponse> diaryResponseList = new ArrayList<>();
        for (Diary diary : diaryList) {
            diaryResponseList.add(new DiaryResponse(diary.getId(), diary.getName()));
        }
        return ResponseEntity.ok(new DiaryListResponse(diaryResponseList));
    }

    @GetMapping("/get/{id}")
    ResponseEntity<DiaryListResponse> getSpecific(@PathVariable Long id){
        diaryService.getDiarySpecific(id);
        return ResponseEntity.ok()
    }
}
