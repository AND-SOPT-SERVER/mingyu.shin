package org.example.diary.repository;

import org.example.diary.repository.entity.Category;
import org.example.diary.repository.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DiaryRepository extends JpaRepository<Diary,Long> {

    @Query("SELECT d FROM Diary d ORDER BY LENGTH(d.body) ASC ")
    List<Diary> findAllOrderByBodyLengthAsc();

    Optional<Diary> findByTitle(String title);

    @Query("SELECT d FROM Diary d WHERE d.category = :category ORDER BY LENGTH(d.body) ASC")
    List<Diary> findAllByCategoryOrderByBodyLengthDesc(Category category);
}
