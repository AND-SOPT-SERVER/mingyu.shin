package org.example.diary.repository;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String title;

    @Column String body;
    @CreatedDate
    LocalDateTime date;

    protected DiaryEntity(){
    }

    private DiaryEntity(final String title, final String body,final LocalDateTime date){
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public static DiaryEntity of(final String title,final String body,final LocalDateTime date){
        validBodyLength(body);
        return new DiaryEntity(title, body,date);
    }

    public long getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

    private static void validBodyLength(final String body){
        if(body.length() > 30){
            throw new IllegalArgumentException();
        }
    }
}
