package com.example.apienglish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.apienglish.entity.AnsLesson;
import com.example.apienglish.entity.Lesson;
import com.example.apienglish.entity.Phonics;




@Repository
public interface AnsLessonRepository extends JpaRepository<AnsLesson, Long> {
	@Query("SELECT al FROM AnsLesson al WHERE al.lesson.lessonId = :lessonId")
    List<AnsLesson> findAnsLessonByLessonId(@Param("lessonId") Long lessonId);
		
	boolean existsByLesson(Lesson lesson);
	
	
}