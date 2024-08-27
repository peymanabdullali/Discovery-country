package com.example.discovery_country.controller;

import com.example.discovery_country.model.dto.request.RoomReservationRequest;
import com.example.discovery_country.model.dto.response.RoomReservationResponse;
import com.example.discovery_country.service.RoomReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<RoomReservationResponse> getReservationByUserId(@PathVariable long id) {
        log.info("Controller.getReservationByUserId start with id:+" + id);
        RoomReservationResponse reservationByUserId = roomReservationService.getReservationByUserId(id);
        log.info("Controller.getReservationByUserId end");
        return ResponseEntity.ok(reservationByUserId);
    }
    @GetMapping("reservationHistory{id}")
    public ResponseEntity<RoomReservationResponse> getReservationHistoryByUserId(@PathVariable long id) {
        log.info("Controller.getReservationByUserId start with id:+" + id);
        RoomReservationResponse reservationByUserId = roomReservationService.getReservationHistoryByUserId(id);
        log.info("Controller.getReservationByUserId end");
        return ResponseEntity.ok(reservationByUserId);
    }
}
