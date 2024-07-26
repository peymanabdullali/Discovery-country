package com.example.discovery_country.controller;

import com.example.discovery_country.model.dto.request.NotificationRequest;
import com.example.discovery_country.model.dto.response.NotificationResponse;
import com.example.discovery_country.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@RequestBody NotificationRequest notificationRequest) {
        log.info("ActionLog.createNotification start");
        NotificationResponse notificationResponse = notificationService.createNotification(notificationRequest);
        log.info("ActionLog.createNotification end");
        return new ResponseEntity<>(notificationResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationResponse> updateNotification(@PathVariable Long id, @RequestBody NotificationRequest notificationRequest) {
        log.info("ActionLog.updateNotification start with id#" + id);
        NotificationResponse notificationResponse = notificationService.updateNotification(id, notificationRequest);
        log.info("ActionLog.updateNotification end with id#" + id);
        return new ResponseEntity<>(notificationResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        log.info("ActionLog.deleteNotification start with id#" + id);
        notificationService.deleteNotification(id);
        log.info("ActionLog.deleteNotification end with id#" + id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long id) {
        log.info("ActionLog.getNotification start with id#" + id);
        NotificationResponse notificationResponse = notificationService.getNotificationById(id);
        log.info("ActionLog.getNotification end with id#" + id);
        return new ResponseEntity<>(notificationResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        log.info("ActionLog.getAllNotifications start");
        List<NotificationResponse> notifications = notificationService.getAllNotifications();
        log.info("ActionLog.getAllNotifications end");
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
}
