package org.example.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity,Long> {

    @Query(value = "SELECT * FROM diary_entity ORDER BY date ASC LIMIT 10", nativeQuery = true)
    List<DiaryEntity> findTop10ByCreatedDate();
}
