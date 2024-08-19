package com.example.discovery_country.dao.repository;

import com.example.discovery_country.dao.entity.RoomEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long>,
        PagingAndSortingRepository<RoomEntity, Long>, JpaSpecificationExecutor<RoomEntity> {
    @Transactional
    @Modifying
    @Query("UPDATE RoomEntity r SET r.available = true " +
            "WHERE r.available = false AND EXISTS (" +
            "SELECT rr FROM RoomReservationEntity rr " +
            "WHERE rr.room.id = r.id AND rr.exitDate <= CURRENT_DATE AND rr.status = true)")
    void updateAvailableRooms();

    @Modifying
    @Query("UPDATE RoomEntity r SET r.available = false WHERE r.id = :id")
    void updateAvailableFalseById(@Param("id") long id);

    @Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query("UPDATE RoomEntity a SET a.deleted = true WHERE a.id = :id")
    void softDelete(Long id);

}
