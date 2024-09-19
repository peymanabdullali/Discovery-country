package com.example.discovery_country.service;

import com.example.discovery_country.client.MailSenderClient;
import com.example.discovery_country.dao.entity.RoomEntity;
import com.example.discovery_country.dao.entity.RoomReservationEntity;

import com.example.discovery_country.dao.entity.auth.User;
import com.example.discovery_country.dao.repository.RoomRepository;
import com.example.discovery_country.dao.repository.RoomReservationRepository;
import com.example.discovery_country.dao.repository.auth.UserRepository;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.mapper.RoomReservationMapper;
import com.example.discovery_country.model.dto.criteria.RoomReservationCriteriaRequest;
import com.example.discovery_country.model.dto.request.RoomReservationRequest;
import com.example.discovery_country.model.dto.request.UserRequest;
import com.example.discovery_country.model.dto.response.RoomReservationResponse;
import com.example.discovery_country.service.specification.RoomReservationSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomReservationService {
    private final RoomReservationRepository roomReservationRepository;
    private final RoomReservationMapper roomReservationMapper;
    private final RoomRepository roomRepository;
    private final MailSenderClient client;
    private final UserRepository userRepository;

    @Transactional
    public RoomReservationResponse createReservation(RoomReservationRequest request) {
        log.info("ActionLog.createReservation start");

        RoomReservationEntity roomReservationEntity = roomReservationMapper.mapToEntity(request);
        calculateAndSetTotalDayAndAmount(roomReservationEntity, request.getRoomId());
        roomRepository.updateAvailableFalseById(request.getRoomId());
        roomReservationRepository.save(roomReservationEntity);
        CompletableFuture.runAsync(()->sendMail(request.getUserId()));
        RoomReservationResponse roomReservationResponse = roomReservationMapper.mapToResponse(roomReservationEntity, LangType.AZ);
        log.info("ActionLog.createReservation end");

        return roomReservationResponse;
    }

    public void sendMail(long id) {
        UserRequest userRequest = new UserRequest();
        User user = userRepository.findUserById(id);
        userRequest.setMail(user.getEmail());
        userRequest.setUsername(user.getUsername());
        client.sendMail(userRequest);
    }

    public RoomReservationResponse getReservationByUserId(long id, LangType key) {
        log.info("ActionLog.getReservationByUserId start with id: " + id);
        RoomReservationEntity roomReservationEntity = roomReservationRepository.
                findRoomReservationEntityByUserIdAndStatusTrue(id).orElseThrow(() -> new RuntimeException("ROOM_RESERVATION_NOT_FOUND"));
        RoomReservationResponse roomReservationResponse = roomReservationMapper.mapToResponse(roomReservationEntity, key);
        log.info("ActionLog.getReservationByUserId end");
        return roomReservationResponse;
    }

    public List<RoomReservationResponse> getReservationHistoryByUserId(long id, LangType key) {
        log.info("ActionLog.getReservationByUserId start with id: " + id);
        List<RoomReservationEntity> entityList = roomReservationRepository.
                findRoomReservationEntitiesByUserIdAndStatusFalse(id);
        List<RoomReservationResponse> list = entityList.stream().map(i ->
                roomReservationMapper.mapToResponse(i, key)).toList();
        log.info("ActionLog.getReservationByUserId end");
        return list;
    }
public void deleteReservation(long id){
        roomReservationRepository.softDelete(id);
}
public List<RoomReservationResponse> getReservations(
        RoomReservationCriteriaRequest request, Pageable pageable){
    Specification<RoomReservationEntity> spec =
            RoomReservationSpecification.getRoomReservationByCriteria(request);
    return roomReservationRepository.findAll(spec, pageable).stream().
            map(i-> roomReservationMapper.mapToResponse(i,request.getLangType())).toList();
}

    public void calculateAndSetTotalDayAndAmount(RoomReservationEntity entity, long id) {
        RoomEntity roomsEntity = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("ROOM_NOT_FOUND"));
        double price = roomsEntity.getPrice();
        byte totalDay = (byte) ChronoUnit.DAYS.between(entity.getEntryDate(), entity.getExitDate());
        entity.setTotalAmount(price * totalDay);
        entity.setTotalDay(totalDay);
    }


}


