package org.example.diary.service;

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

    public String getTitle() {
        return title;
    }
}
