package com.example.discovery_country.dao.repository;

import com.example.discovery_country.dao.entity.RestaurantEntity;
import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long>,
        PagingAndSortingRepository<RestaurantEntity, Long>, JpaSpecificationExecutor<RestaurantEntity> {
    Optional<RestaurantEntity> findByIdAndStatusFalse(long id);

    @Modifying
    @Transactional
    @Query("UPDATE RestaurantEntity a SET a.status = true WHERE a.id = :id")
    void softDelete(Long id);

}
