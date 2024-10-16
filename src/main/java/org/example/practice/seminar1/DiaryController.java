package org.example.practice.seminar1;

import java.util.List;

public class DiaryController {
    private Status status = Status.READY;
    private final DiaryService diaryService = new DiaryService();

    Status getStatus() {
        return status;
    }

    void boot() {
        this.status = Status.RUNNING;
    }

    void finish() {
        this.status = Status.FINISHED;
    }

    // APIS
    final List<Diary> getList() {
        return diaryService.getDiaryList();
    }

    final void post(final String body) {
        diaryService.createDiary(body);
    }

    final void delete(final String id) {
        diaryService.deleteDiary(Long.parseLong(id));
    }

    final void patch(final String id, final String body) {
        diaryService.updateDiary(Long.parseLong(id), body);
    }

    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }
}