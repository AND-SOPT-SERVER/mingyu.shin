package org.example.diary.repository;

import org.example.diary.repository.entity.Category;
import org.example.diary.repository.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity,Long> {

    @Query("SELECT d FROM DiaryEntity d ORDER BY LENGTH(d.body) ASC ")
    List<DiaryEntity> findAllOrderByBodyLengthAsc();

    Optional<DiaryEntity> findByTitle(String title);

    @Query("SELECT d FROM DiaryEntity d WHERE d.category = :category ORDER BY LENGTH(d.body) ASC")
    List<DiaryEntity> findAllByCategoryOrderByBodyLengthDesc(Category category);
}
