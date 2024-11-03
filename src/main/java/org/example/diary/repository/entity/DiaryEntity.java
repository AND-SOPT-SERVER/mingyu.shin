package org.example.diary.repository.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
public class DiaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private Category category;

    @Column
    private String body;
    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected DiaryEntity() {
    }

    private DiaryEntity(
            final String title,
            final String body,
            final LocalDateTime createdAt,
            final Category category,
            final Member member
    ) {
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
        this.category = category;
        this.member = member;
    }


    public static DiaryEntity of(
            final String title,
            final String body,
            final Category category,
            final LocalDateTime date,
            final Member member
    ) {
        return new DiaryEntity(title, body, date, category, member);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Member getMember() {
        return member;
    }

    public void setBody(final String body) {
        this.body = body;
    }
}
