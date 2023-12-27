package com.example.apienglish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.apienglish.entity.AnsReview;
import com.example.apienglish.entity.Lesson;

@Repository
public interface AnsReviewRepository extends JpaRepository<AnsReview, Long>{
	@Query("SELECT ar FROM AnsReview ar WHERE ar.lesson.lessonId = :lessonId")
    List<AnsReview> findAnsReviewByLessonId(@Param("lessonId") Long lessonId);
		
	boolean existsByLesson(Lesson lesson);
}
