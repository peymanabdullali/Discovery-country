package com.example.discovery_country.dao.repository;

import com.example.discovery_country.dao.entity.RoomsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomsEntity, Long>,
        PagingAndSortingRepository<RoomsEntity, Long>, JpaSpecificationExecutor<RoomsEntity> {
    @Transactional
    @Modifying
    @Query("UPDATE RoomsEntity r SET r.available = false " +
            "WHERE r.available = true AND EXISTS (" +
            "SELECT rr FROM RoomReservationEntity rr " +
            "WHERE rr.room.id = r.id AND rr.exitDate <= CURRENT_DATE AND rr.status = false)")
    void updateAvailableRooms();

    @Modifying
    @Query("UPDATE RoomsEntity r SET r.available = true WHERE r.id = :id")
    void updateAvailableTrueById(@Param("id") long id);

}
