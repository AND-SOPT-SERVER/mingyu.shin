package org.example.diary.repository.adapter;


import org.example.diary.repository.MemberRepository;
import org.example.diary.repository.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberSaver {

    private final MemberRepository memberRepository;

    MemberSaver(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(final Member member){
        return memberRepository.save(member);
    }
}
