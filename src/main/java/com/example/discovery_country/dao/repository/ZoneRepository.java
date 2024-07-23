package com.example.discovery_country.dao.repository;

import com.example.discovery_country.dao.entity.ZoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<ZoneEntity, Long> {
}
