package org.example.practice.seminar1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private final Map<Long, String> storage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();

    public void save(final Diary diary) {
        final long id = numbering.addAndGet(1);
        storage.put(id, diary.getBody());
    }

    public List<Diary> findAll() {
        List<Diary> diaries = new ArrayList<>();
        for (long index = 1; index <= numbering.longValue(); index++) {
            final String body = storage.get(index);
            diaries.add(Diary.of(index, body));
        }
        return diaries;
    }

    public void updateDiaryById(final long id,final String body) {
        searchDiaryById(id);
        update(id, Diary.of(id, body));
    }

    public void deleteDiaryById(final long id) {
        searchDiaryById(id);
        for (long i = id; i < numbering.get(); i++) {
            storage.put(i, storage.get(i + 1));
        }
        storage.remove(numbering.getAndDecrement());
    }

    private void update(final long id, Diary diary) {
        storage.put(id, diary.getBody());
    }

    private void searchDiaryById(final long id) {
        if (!storage.containsKey(id)) {
            throw new NoSuchElementException("아이디가 존재하지 않습니다");
        }
    }
}
