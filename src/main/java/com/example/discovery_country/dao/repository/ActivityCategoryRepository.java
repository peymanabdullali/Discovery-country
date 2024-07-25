package com.example.discovery_country.dao.repository;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ActivityCategoryRepository extends JpaRepository<ActivityCategoryEntity, Long>,
        PagingAndSortingRepository<ActivityCategoryEntity,Long>, JpaSpecificationExecutor<ActivityCategoryEntity>   {

    @Modifying
    @Transactional
    @Query("UPDATE ActivityCategoryEntity a SET a.deleted = true WHERE a.id = :id")
    void softDelete(Long id);



}
