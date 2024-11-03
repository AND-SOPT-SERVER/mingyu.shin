package org.example.diary.repository.entity;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String nickname;

    public Member(final String userName, final String password, final String nickname) {
        this.userName = userName;
        this.password = password;
        this.nickname = nickname;
    }

    public Member() {

    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }
}
