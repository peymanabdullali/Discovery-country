package com.example.discovery_country.dao.repository;

import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ScenicSpotRepository extends JpaRepository<ScenicSpotEntity, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE ScenicSpotEntity a SET a.checkStatus = true WHERE a.id = :id")
    void updateStatus(Long id);


}
