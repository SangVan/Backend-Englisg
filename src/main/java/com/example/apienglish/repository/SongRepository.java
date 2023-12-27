package com.example.apienglish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.apienglish.entity.Songs;
import com.example.apienglish.entity.Stories;
import com.example.apienglish.entity.Unit;



@Repository
public interface SongRepository extends JpaRepository<Songs, Long> {
	@Query("SELECT s FROM Songs s WHERE s.unit.unitId = :unitId")
    List<Songs> findSongsByUnitId(@Param("unitId") Long unitId);
	
	@Query("SELECT s FROM Songs s WHERE s.unit.unitId = :unitId")
    Songs findOneSongsByUnitId(@Param("unitId") Long unitId);
	
	boolean existsByUnit(Unit unit);
}
