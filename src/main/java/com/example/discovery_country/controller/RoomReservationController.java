package com.example.discovery_country.controller;

import com.example.discovery_country.model.dto.request.RoomReservationRequest;
import com.example.discovery_country.model.dto.response.RoomReservationResponse;
import com.example.discovery_country.service.RoomReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roomReservations")
@Slf4j
@RequiredArgsConstructor
public class RoomReservationController {
    private final RoomReservationService roomReservationService;

    @PostMapping
    public ResponseEntity<RoomReservationResponse> createReservation(@RequestBody RoomReservationRequest request) {
        RoomReservationResponse reservation = roomReservationService.createReservation(request);
        return ResponseEntity.ok(reservation);


    }
}
