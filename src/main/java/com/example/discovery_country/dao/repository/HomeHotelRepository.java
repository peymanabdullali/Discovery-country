package com.example.discovery_country.dao.repository;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.ImageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface HomeHotelRepository extends JpaRepository<HomeHotelEntity, Long>,
        PagingAndSortingRepository<HomeHotelEntity, Long>, JpaSpecificationExecutor<HomeHotelEntity> {
    @Modifying
    @Transactional
    @Query("UPDATE HomeHotelEntity a SET a.deleted = true WHERE a.id = :id")
    void softDelete(Long id);

}
