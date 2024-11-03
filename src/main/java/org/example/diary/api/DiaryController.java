package org.example.diary.api;

import org.example.diary.api.dto.request.DiaryCreateRequest;
import org.example.diary.api.dto.request.DiaryGetByCategoryRequest;
import org.example.diary.api.dto.request.DiaryUpdateRequest;
import org.example.diary.api.dto.response.DiaryListResponse;
import org.example.diary.api.dto.response.DiaryResponse;
import org.example.diary.api.dto.response.DiarySpecificResponse;
import org.example.diary.common.util.validator.diary.DiaryValidator;
import org.example.diary.service.DiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.diary.common.constants.TempValue.TEMP_TITLE;

@RestController
@RequestMapping("/diaries")
public class DiaryController {

    private final DiaryService diaryService;
    private final DiaryValidator diaryValidator;

    public DiaryController(DiaryService diaryService, DiaryValidator diaryValidator) {
        this.diaryService = diaryService;
        this.diaryValidator = diaryValidator;
    }

    @PostMapping
    ResponseEntity<Void> post(@RequestBody DiaryCreateRequest request) {
        diaryValidator.validate(request.title(), request.body());
        diaryService.createDiary(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    ResponseEntity<DiaryListResponse> get() {
        List<DiaryResponse> response = diaryService.getList();
        return ResponseEntity.ok(new DiaryListResponse(response));
    }

    @GetMapping("/category")
    ResponseEntity<DiaryListResponse> getByCategory(@RequestBody final DiaryGetByCategoryRequest request){
        List<DiaryResponse> diaryList = diaryService.getListByCategory(request.category());
        return ResponseEntity.ok(new DiaryListResponse(diaryList));
    }

    @GetMapping("/{id}")
    ResponseEntity<DiarySpecificResponse> getSpecific(@PathVariable final long id) {
        DiarySpecificResponse response =  diaryService.getDiarySpecific(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    ResponseEntity<Void> updateDiary(@PathVariable final long id,@RequestBody final DiaryUpdateRequest request) {
        diaryValidator.validate(TEMP_TITLE.name(), request.body());
        diaryService.updateDiary(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteDiary(@PathVariable final long id){
        diaryService.deleteDiary(id);
        return ResponseEntity.ok().build();
    }
}
