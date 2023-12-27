package com.example.apienglish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.apienglish.entity.Phonics;
import com.example.apienglish.entity.Stories;
import com.example.apienglish.entity.Unit;



@Repository
public interface PhonicRepository extends JpaRepository<Phonics, Long> {
	@Query("SELECT p FROM Phonics p WHERE p.unit.unitId = :unitId")
    List<Phonics> findPhonicsByUnitId(@Param("unitId") Long unitId);
	
	@Query("SELECT p FROM Phonics p WHERE p.unit.unitId = :unitId")
    Phonics findOnePhonicsByUnitId(@Param("unitId") Long unitId);

	boolean existsByUnit(Unit unit);
	
	
}
