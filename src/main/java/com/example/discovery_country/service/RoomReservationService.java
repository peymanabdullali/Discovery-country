package com.example.discovery_country.service;

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

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomReservationService {
    private final RoomReservationRepository roomReservationRepository;
    private final RoomReservationMapper roomReservationMapper;
    private final RoomRepository roomRepository;

    @Transactional
    public RoomReservationResponse createReservation(RoomReservationRequest request) {
        RoomReservationEntity roomReservationEntity = roomReservationMapper.mapToEntity(request);
        roomRepository.updateAvailableFalseById(request.getRoomId());
        roomReservationRepository.save(roomReservationEntity);
        return roomReservationMapper.mapToResponse(roomReservationEntity);  }



}
