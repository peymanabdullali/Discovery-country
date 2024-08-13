package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.RoomEntity;
import com.example.discovery_country.dao.entity.RoomReservationEntity;

import com.example.discovery_country.dao.repository.RoomRepository;
import com.example.discovery_country.dao.repository.RoomReservationRepository;
import com.example.discovery_country.mapper.RoomReservationMapper;
import com.example.discovery_country.model.dto.request.RoomReservationRequest;
import com.example.discovery_country.model.dto.response.RoomReservationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomReservationService {
    private final RoomReservationRepository roomReservationRepository;
    private final RoomReservationMapper roomReservationMapper;
    private final RoomRepository roomRepository;

    @Transactional
    public RoomReservationResponse createReservation(RoomReservationRequest request) {
        log.info("ActionLog.createReservation start");

        RoomReservationEntity roomReservationEntity = roomReservationMapper.mapToEntity(request);
        calculateAndSetTotalDayAndAmount(roomReservationEntity, request.getRoomId());
        roomRepository.updateAvailableTrueById(request.getRoomId());
        roomReservationRepository.save(roomReservationEntity);
        RoomReservationResponse roomReservationResponse = roomReservationMapper.mapToResponse(roomReservationEntity);
        log.info("ActionLog.createReservation end");

        return roomReservationResponse;
    }

    public RoomReservationResponse getReservationByUserId(long id) {
        log.info("ActionLog.getReservationByUserId start with id: " + id);
        RoomReservationEntity roomReservationEntity = roomReservationRepository.
                findRoomReservationEntitiesByUserIdAndStatusFalse(id).orElseThrow(() -> new RuntimeException("ROOM_RESERVATION_NOT_FOUND"));
        RoomReservationResponse roomReservationResponse = roomReservationMapper.mapToResponse(roomReservationEntity);
        log.info("ActionLog.getReservationByUserId end");
        return roomReservationResponse;
    }
    public RoomReservationResponse getReservationHistoryByUserId(long id) {
        log.info("ActionLog.getReservationByUserId start with id: " + id);
        RoomReservationEntity roomReservationEntity = roomReservationRepository.
                findRoomReservationEntitiesByUserIdAndStatusTrue(id).orElseThrow(() -> new RuntimeException("ROOM_RESERVATION_NOT_FOUND"));
        RoomReservationResponse roomReservationResponse = roomReservationMapper.mapToResponse(roomReservationEntity);
        log.info("ActionLog.getReservationByUserId end");
        return roomReservationResponse;
    }


    public void calculateAndSetTotalDayAndAmount(RoomReservationEntity entity, long id) {
        RoomEntity roomsEntity = roomRepository.findById(id).orElseThrow(()->new RuntimeException("ROOM_NOT_FOUND"));
        double price = roomsEntity.getPrice();
        byte totalDay = (byte) ChronoUnit.DAYS.between(entity.getEntryDate(), entity.getExitDate());
        entity.setTotalAmount(price * totalDay);
        entity.setTotalDay(totalDay);
    }


}


