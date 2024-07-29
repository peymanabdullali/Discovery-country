package com.example.discovery_country.dao.repository;

import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>, PagingAndSortingRepository<ReviewEntity,Long>,
        JpaSpecificationExecutor<ReviewEntity> {
}
