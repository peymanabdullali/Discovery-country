package com.example.discovery_country.dao.repository;

import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenicSpotRepository extends JpaRepository<ScenicSpotEntity, Long> {
}
