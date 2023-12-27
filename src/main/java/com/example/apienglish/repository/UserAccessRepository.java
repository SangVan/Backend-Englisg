package com.example.apienglish.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.apienglish.entity.UserAccess;

@Repository
public interface UserAccessRepository extends JpaRepository<UserAccess, Long> {
	// Số truy cập theo ngày
	  @Query("SELECT COUNT(ua) FROM UserAccess ua WHERE ua.accessDate = :accessDate")
	  Long countByAccessDate(@Param("accessDate") String accessDate);
	  
	  @Query("SELECT ua.accessDate AS AccessDate, COUNT(ua) AS TotalAccess"
	  		+ " FROM UserAccess ua WHERE ua.accessDate BETWEEN :startDate AND :endDate"
	  		+ " AND ua.level.id = :levelId GROUP BY ua.accessDate")
	    List<Map<String, Object>> countAccessByDateAndLevel(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("levelId") Long levelId);
	  
	  
		 @Query("SELECT COUNT(ua) FROM UserAccess ua WHERE ua.level.levelId = :levelId AND ua.accessDate = :today")
		 Long countAccessByLevelId(@Param("levelId") Long levelId, @Param("today") String today);

}
