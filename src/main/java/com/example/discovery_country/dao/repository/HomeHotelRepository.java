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

    @EntityGraph(attributePaths = {"images"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<HomeHotelEntity> findAll(Specification<HomeHotelEntity> spec, Pageable pageable);

//    @Query("SELECT h FROM HomeHotelEntity h " +
//            "LEFT JOIN FETCH h.reviews r " +
//            "LEFT JOIN FETCH h.images i " +
//            "LEFT JOIN FETCH h.rooms ro " +
//            "WHERE h.id = :id " +
//            "AND (r IS NULL OR r.status = false) " +
//            "AND (i IS NULL OR i.deleted = false) " +
//            "AND (ro IS NULL OR (ro.available = true AND ro.deleted = false))")
//    Optional<HomeHotelEntity> findByIdWithFilters(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE HomeHotelEntity a SET a.deleted = true WHERE a.id = :id")
    void softDelete(Long id);

}
