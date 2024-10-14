package org.example.diary.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Diary {
    private final long id;
    private final String title;

    public Diary(long id, String title){
        this.id = id;
        this.title = title;
    }

    public long getId(){
        return id;
    }

    public String getName() {
        return title;
    }
}
