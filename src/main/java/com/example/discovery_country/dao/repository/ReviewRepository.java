package com.example.discovery_country.dao.repository;

import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>, PagingAndSortingRepository<ReviewEntity,Long>,
        JpaSpecificationExecutor<ReviewEntity> {
    @Modifying
    @Transactional
    @Query("UPDATE ReviewEntity a SET a.status = true WHERE a.id = :id")
    void softDelete(Long id);
}
