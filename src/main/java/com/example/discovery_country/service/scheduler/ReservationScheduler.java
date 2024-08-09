package com.example.discovery_country.service.scheduler;

import com.example.discovery_country.dao.repository.RoomRepository;
import com.example.discovery_country.dao.repository.RoomReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationScheduler {
     private final RoomRepository roomRepository;
     private final RoomReservationRepository reservationRepository;
//    @Scheduled(cron = "0 0 12,0 * * ?")
@Scheduled(cron = "*/10 * * * * *")
public void updateRoomAvailability(){
    System.out.println("salam");
        roomRepository.updateAllRoomsAvailability();
        reservationRepository.updateRoomReservationStatus();
    }
}
