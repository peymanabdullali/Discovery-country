package com.example.discovery_country.dao.repository;

import com.example.discovery_country.dao.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE ImageEntity a SET a.deleted = true WHERE a.id = :id")
    void softDelete(Long id);
}
