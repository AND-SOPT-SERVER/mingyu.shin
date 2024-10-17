package org.example.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity,Long> {

    @Query("SELECT d FROM DiaryEntity d ORDER BY LENGTH(d.body) DESC")
    List<DiaryEntity> findAllOrderByBodyLengthDesc();;

    Optional<DiaryEntity> findByTitle(String title);

    List<DiaryEntity> findAllByCategory(Category category);
}
