package com.example.discovery_country.dao.repository;

import com.example.discovery_country.dao.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Long> {
}
