package com.example.apienglish.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.apienglish.entity.Account;
import com.example.apienglish.entity.Unit;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
  
  Optional<Account> findByEmailAndVerificationCode(String email, String verificationCode);
  
//Đếm số người dùng đã đăng ký
  @Query("SELECT COUNT(a) FROM Account a JOIN a.levels l")
  Long countAccounts();
  
  //Đếm số người dùng theo cấp độ
  @Query("SELECT COUNT(a) FROM Account a JOIN a.levels l WHERE l.levelId = :levelId")
  Long countAccountsByLevelId(@Param("levelId") Long levelId);
}
