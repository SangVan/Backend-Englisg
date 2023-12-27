package com.example.apienglish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.apienglish.entity.Grammar;
import com.example.apienglish.entity.Lesson;
import com.example.apienglish.entity.Unit;


@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
	@Query("SELECT g FROM Lesson g WHERE g.unit.unitId = :unitId")
    List<Lesson> findLessonByUnitId(@Param("unitId") Long unitId);
	
	
	boolean existsByUnit(Unit unit);
}
