package org.example.practice.seminar1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private Map<Long, String> storage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();
    private final String filePath = "diary_storage.ser";
    private boolean init = false;

    void saveDiary(final Diary diary) throws IOException, ClassNotFoundException {
        init();
        final long id = numbering.addAndGet(1);
        save(id, diary.getBody());
    }

    List<Diary> findAll() throws IOException, ClassNotFoundException {
        init();
        List<Diary> diaries = new ArrayList<>();
        for (long index = 1; index <= numbering.longValue(); index++) {
            final String body = storage.get(index);
            diaries.add(Diary.of(index, body));
        }
        return diaries;
    }

    void updateDiaryById(final long id, final String body) throws IOException, ClassNotFoundException {
        init();
        searchDiaryById(id);
        update(id, Diary.of(id, body));
    }

    void deleteDiaryById(final long id) throws IOException, ClassNotFoundException {
        init();
        searchDiaryById(id);
        delete(id);
    }

    private void init() throws IOException, ClassNotFoundException {
        FileChannel channel = FileChannel.open(Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.READ);
        FileLock lock = channel.lock(0, Long.MAX_VALUE, true);
        if (channel.size() != 0) {
            ObjectInputStream ois = new ObjectInputStream(Channels.newInputStream(channel));
            storage = (ConcurrentHashMap<Long, String>) ois.readObject();
            numbering.set(storage.size());
            for (long index = 1; index <= numbering.longValue(); index++) {
                storage.put(index, storage.get(index));
            }
            init = true;
        }
        lock.release();

    }

    private void update(final long id, Diary diary) throws IOException {
        save(id, diary.getBody());
    }

    private void searchDiaryById(final long id) {
        if (!storage.containsKey(id)) {
            throw new NoSuchElementException("아이디가 존재하지 않습니다");
        }
    }

    private void save(long id, String body) throws IOException {
        FileChannel channel = FileChannel.open(Paths.get(filePath), StandardOpenOption.CREATE,
                StandardOpenOption.WRITE);
        FileLock lock = channel.lock();
        ObjectOutputStream oos = new ObjectOutputStream(Channels.newOutputStream(channel));
        storage.put(id, body);
        oos.writeObject(storage);
        lock.release();
    }

    private void delete(final long id) throws IOException {
        FileChannel channel = FileChannel.open(Paths.get(filePath), StandardOpenOption.CREATE,
                StandardOpenOption.WRITE);
        FileLock lock = channel.lock();
        ObjectOutputStream oos = new ObjectOutputStream(Channels.newOutputStream(channel));
        for (long i = id; i < numbering.longValue(); i++) {
            storage.put(i, storage.get(i + 1));
        }
        storage.remove(numbering.getAndDecrement());
        oos.writeObject(storage);
        lock.release();
    }
}
