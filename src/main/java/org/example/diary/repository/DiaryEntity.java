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

    @Column
    public Category category;

    @Column String body;
    @CreatedDate
    LocalDateTime date;

    protected DiaryEntity(){
    }

    private DiaryEntity(final String title, final String body,final LocalDateTime date,final Category category){
        this.title = title;
        this.body = body;
        this.date = date;
        this.category = category;
    }

    public static DiaryEntity of(final String title, final String body, final Category category, final LocalDateTime date){
        validBodyLength(body);
        return new DiaryEntity(title, body,date,category);
    }

    public long getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getBody() {
        return body;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setBody(final String body){
        validBodyLength(body);
        this.body = body;
    }

    private static void validBodyLength(final String body){
        if(body.length() > 30){
            throw new IllegalArgumentException("일기의 글자수는 30자 이하입니다."); //413 Content Too Large, 400
        }
    }
}
