package com.example.discovery_country.dao.repository.auth;


import com.example.discovery_country.dao.entity.auth.ForgotPassword;
import com.example.discovery_country.dao.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer> {

    ForgotPassword findByUser(User user);

    @Query("select fp from ForgotPassword fp where fp.otp = ?1 and fp.user = ?2")

    Optional<ForgotPassword> findByOtpAndUser(Integer otp, User user);
}