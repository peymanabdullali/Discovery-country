package com.example.discovery_country.dao.repository;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.RoomReservationEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservationEntity, Long>,
        PagingAndSortingRepository<RoomReservationEntity, Long>, JpaSpecificationExecutor<RoomReservationEntity> {
    @Transactional
    @Modifying
    @Query("UPDATE RoomReservationEntity  SET status = true " +
            "WHERE exitDate <= CURRENT_DATE AND status = false ")
    void updateRoomReservationStatus();
    Optional<RoomReservationEntity> findRoomReservationEntitiesByUserIdAndStatusTrue(long id);
    Optional<RoomReservationEntity> findRoomReservationEntitiesByUserIdAndStatusFalse(long id);

}
