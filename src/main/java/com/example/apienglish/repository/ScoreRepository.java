package com.example.apienglish.repository;

import com.example.apienglish.entity.Score;
import com.example.apienglish.entity.Unit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    // Các phương thức truy vấn đặc biệt nếu cần
	@Query("SELECT s FROM Score s WHERE s.account.id = :accountId")
	List<Score> getAllScoresByAccountId(@Param("accountId") Long accountId);
	
	@Query("SELECT s FROM Score s WHERE s.unit.unitId = :unitId")
	List<Score> getAllScoresByUnitId(@Param("unitId") Long unitId);	
}
