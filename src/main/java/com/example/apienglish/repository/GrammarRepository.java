package com.example.apienglish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.apienglish.entity.Grammar;
import com.example.apienglish.entity.Songs;
import com.example.apienglish.entity.Unit;


@Repository
public interface GrammarRepository extends JpaRepository<Grammar, Long> {
	@Query("SELECT g FROM Grammar g WHERE g.unit.unitId = :unitId")
    List<Grammar> findGrammarsByUnitId(@Param("unitId") Long unitId);
	
	@Query("SELECT g FROM Grammar g WHERE g.unit.unitId = :unitId")
    Grammar findOneGrammarsByUnitId(@Param("unitId") Long unitId);
	
	boolean existsByUnit(Unit unit);
}
