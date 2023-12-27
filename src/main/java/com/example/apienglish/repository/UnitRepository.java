package com.example.apienglish.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.apienglish.entity.Unit;

import jakarta.transaction.Transactional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
	
	@Query("SELECT u FROM Unit u WHERE u.level.levelId = :levelId")
	List<Unit> findUnitsByLevelId(@Param("levelId") Long levelId);
	
	//Đếm số bài học đã tạo
	@Query("SELECT COUNT(u) FROM Unit u")
    Long countUnits();
	
	//Đếm số bài học theo cấp độ
	 @Query("SELECT COUNT(u) FROM Unit u WHERE u.level.levelId = :levelId")
	 Long countUnitsByLevelId(@Param("levelId") Long levelId);
	 
//	 @Query("SELECT u FROM Unit u WHERE LOWER(u.unitName) = LOWER('Test')")
//	 List<Unit> findUnitsByUnitNameTest();
}