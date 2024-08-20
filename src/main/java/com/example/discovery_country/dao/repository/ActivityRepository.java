package com.example.discovery_country.dao.repository;

import com.example.discovery_country.dao.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> ,
        PagingAndSortingRepository<ActivityEntity, Long>, JpaSpecificationExecutor<ActivityEntity> {
    @Modifying
    @Transactional
    @Query("UPDATE ActivityEntity a SET a.deleted = true WHERE a.id = :id")
    void softDelete(Long id);

}
