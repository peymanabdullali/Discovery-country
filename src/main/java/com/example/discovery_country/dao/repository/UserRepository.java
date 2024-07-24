package com.example.discovery_country.dao.repository;

import com.example.discovery_country.dao.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String username);

//    @Transactional
//    @Modifying
//    @Query("update UserRequest u set u.password = ?2 where u.email = ?1")
//    void updatePassword(String email, String password);
//
//
//    @Modifying
//    @Transactional
//    @Query("update UserRequest u set u.failedAttempt = ?1 WHERE u.email = ?2")
//    void updateFailedAttempts(int failAttempts, String email);
//
//    @Modifying
//    @Query("UPDATE UserRequest u SET u.accountNonLocked = ?1, u.lockTime = ?2 WHERE u.email = ?3")
//    void updateLockStatus(boolean accountNonLocked, Date lockTime, String email);
}