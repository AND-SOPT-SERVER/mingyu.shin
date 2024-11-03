package org.example.diary.repository.adapter;

import org.example.diary.repository.MemberRepository;
import org.example.diary.repository.entity.Member;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MemberFinder {

    public MemberFinder(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private final MemberRepository memberRepository;

    public Optional<Member> findById(final long id){
        return memberRepository.findById(id);
    }

    public Member findMemberById(final long id){
        return memberRepository.findById(id).orElseThrow(()-> new RuntimeException("유저를 찾을 수 없습니다."));
    }

    public Member findUserByUserName(final String userName){
        return memberRepository.findByUserName(userName);
    }
}
