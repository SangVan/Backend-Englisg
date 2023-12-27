package com.example.apienglish.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.apienglish.entity.AnsGrammar;
import com.example.apienglish.entity.Grammar;
import com.example.apienglish.entity.Phonics;
import com.example.apienglish.entity.Songs;
import com.example.apienglish.entity.Unit;

@Repository
public interface AnsGrammarRepository extends JpaRepository<AnsGrammar, Integer> {
    // Các phương thức tùy chỉnh có thể được thêm vào đây nếu cần thiết
	
	@Query("SELECT ag FROM AnsGrammar ag WHERE ag.unit.unitId = :unitId")
    List<AnsGrammar> findAnsGrammarsByUnitId(@Param("unitId") Long unitId);

	
	boolean existsByUnit(Unit unit);
}
