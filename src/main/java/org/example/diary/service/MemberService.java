package org.example.diary.service;

import org.example.diary.api.dto.request.MemberCreateRequest;
import org.example.diary.api.dto.request.SignInRequest;
import org.example.diary.repository.adapter.MemberFinder;
import org.example.diary.repository.adapter.MemberSaver;
import org.example.diary.repository.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberService {

    private final MemberFinder memberFinder;
    private final MemberSaver memberSaver;


    public MemberService(MemberFinder memberFinder, MemberSaver memberSaver) {
        this.memberFinder = memberFinder;
        this.memberSaver = memberSaver;
    }

    public void createMember(MemberCreateRequest request){
        memberSaver.createMember(new Member(request.userName(), request.password(), request.nickname()));
    }

    public long signIn(SignInRequest request){
        Member member = memberFinder.findUserByUserName(request.username());
        if (!member.getPassword().equals(request.password())){
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }
        return member.getId();
    }
}
