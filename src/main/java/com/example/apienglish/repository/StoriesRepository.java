package com.example.apienglish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.apienglish.entity.Stories;
import com.example.apienglish.entity.Unit;

@Repository
public interface StoriesRepository  extends JpaRepository<Stories, Long>{
	@Query("SELECT st FROM Stories st WHERE st.unit.unitId = :unitId")
    List<Stories> findStoriesByUnitId(@Param("unitId") Long unitId);
	
	@Query("SELECT st FROM Stories st WHERE st.unit.unitId = :unitId")
	Stories findOneStoriesByUnitId(@Param("unitId") Long unitId);
	
	@Query("DELETE FROM Stories st WHERE st.unit.unitId = :unitId")
	void deleteStoriesByUnitId(@Param("unitId") Long unitId);
	
	boolean existsByUnit(Unit unit);
}
