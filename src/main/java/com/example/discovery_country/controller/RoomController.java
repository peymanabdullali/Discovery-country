package com.example.discovery_country.controller;

import com.example.discovery_country.model.dto.criteria.RoomCriteriaRequest;
import com.example.discovery_country.model.dto.request.RoomRequest;
import com.example.discovery_country.model.dto.response.RoomResponse;
import com.example.discovery_country.model.dto.response.RoomResponseFindById;
import com.example.discovery_country.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponse> createRoom( @RequestBody RoomRequest roomRequest) {
        RoomResponse roomResponse = roomService.create(roomRequest);
        return new ResponseEntity<>(roomResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<RoomResponse>> getRooms(RoomCriteriaRequest criteriaRequest, Pageable pageable) {
        Page<RoomResponse> rooms = roomService.getRooms(criteriaRequest, pageable);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseFindById> getRoomById(@PathVariable Long id) {
        RoomResponseFindById roomResponse = roomService.roomResponseFindById(id);
        return new ResponseEntity<>(roomResponse, HttpStatus.OK);
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<RoomResponse> getRoom(@PathVariable Long id) {
        RoomResponse roomResponse = roomService.getRoom(id);
        return new ResponseEntity<>(roomResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateRoom(@PathVariable Long id,  @RequestBody RoomRequest roomRequest) {
        RoomResponse updatedRoom = roomService.update(id, roomRequest);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.softDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
