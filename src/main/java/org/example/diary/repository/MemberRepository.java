package org.example.diary.repository;

import org.example.diary.repository.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByUserName(String userName);
}
