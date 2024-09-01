package com.example.discovery_country.controller;

import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.model.dto.request.RoomReservationRequest;
import com.example.discovery_country.model.dto.response.RoomReservationResponse;
import com.example.discovery_country.service.RoomReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roomReservations")
@Slf4j
@RequiredArgsConstructor
public class RoomReservationController {
    private final RoomReservationService roomReservationService;
//
    @PostMapping
    public ResponseEntity<RoomReservationResponse> createReservation(@RequestBody RoomReservationRequest request) {
        log.info("Controller.createReservation start");
        RoomReservationResponse reservation = roomReservationService.createReservation(request);
        log.info("Controller.createReservation end");
        return ResponseEntity.ok(reservation);
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
