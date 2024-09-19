package com.example.discovery_country.controller;

import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.model.dto.criteria.RoomReservationCriteriaRequest;
import com.example.discovery_country.model.dto.request.RoomReservationRequest;
import com.example.discovery_country.model.dto.response.RoomReservationResponse;
import com.example.discovery_country.service.RoomReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/roomReservations")
@Slf4j
@RequiredArgsConstructor
public class RoomReservationController {
    private final RoomReservationService roomReservationService;

    @PostMapping("/create")
    public ResponseEntity<RoomReservationResponse> createReservation(@RequestBody RoomReservationRequest request) {

        log.info("Controller.createReservation start");
        RoomReservationResponse reservation = roomReservationService.createReservation(request);
        log.info("Controller.createReservation end");
        return ResponseEntity.ok(reservation);
    }

    @GetMapping
    public Page<RoomReservationResponse> getReservations(RoomReservationCriteriaRequest request,
                                                         Pageable pageable) {
        return new PageImpl<>(roomReservationService.getReservations(request, pageable));
    }

    @GetMapping("delete{id}")
    public void deleteReservation(@PathVariable long id) {
        roomReservationService.deleteReservation(id);
    }

    @GetMapping("reservation{id}")
    public ResponseEntity<RoomReservationResponse> getReservationByUserId(@PathVariable long id, LangType key) {
        log.info("Controller.getReservationByUserId start with id:+" + id);
        RoomReservationResponse reservationByUserId = roomReservationService.getReservationByUserId(id,key);
        log.info("Controller.getReservationByUserId end");
        return ResponseEntity.ok(reservationByUserId);
    }
    @GetMapping("reservationHistory{id}")
    public ResponseEntity<List<RoomReservationResponse>> getReservationHistoryByUserId(@PathVariable long id, LangType key) {
        log.info("Controller.getReservationByUserId start with id:+" + id);
        List<RoomReservationResponse> reservationByUserId = roomReservationService.getReservationHistoryByUserId(id,key);
        log.info("Controller.getReservationByUserId end");
        return ResponseEntity.ok(reservationByUserId);
    }
}
