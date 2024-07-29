package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.NotificationEntity;
import com.example.discovery_country.dao.repository.NotificationRepository;
import com.example.discovery_country.exception.NotificationNotFoundException;
import com.example.discovery_country.mapper.NotificationMapper;
import com.example.discovery_country.model.dto.request.NotificationRequest;
import com.example.discovery_country.model.dto.response.NotificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationResponse createNotification(NotificationRequest notificationRequest) {
        log.info("ActionLog.createNotification start");

        NotificationEntity notificationEntity = notificationMapper.mapToEntity(notificationRequest);
        NotificationEntity savedNotification = notificationRepository.save(notificationEntity);

        log.info("ActionLog.createNotification end");
        return notificationMapper.mapToResponse(savedNotification);
    }

    public NotificationResponse updateNotification(Long id, NotificationRequest notificationRequest) {
        log.info("ActionLog.updateNotification start with id#" + id);

        NotificationEntity notificationEntity = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException(HttpStatus.NOT_FOUND.name(), "Notification not found"));
        notificationMapper.mapForUpdate(notificationEntity, notificationRequest);
        NotificationEntity updatedNotification = notificationRepository.save(notificationEntity);

        log.info("ActionLog.updateNotification end with id#" + id);
        return notificationMapper.mapToResponse(updatedNotification);
    }

    public void deleteNotification(Long id) {
        log.info("ActionLog.deleteNotification start with id#" + id);

        NotificationEntity notificationEntity = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException(HttpStatus.NOT_FOUND.name(), "Notification not found"));
        notificationEntity.setDeleted(true);
        notificationRepository.save(notificationEntity);

        log.info("ActionLog.deleteNotification end with id#" + id);
    }

    public NotificationResponse getNotificationById(Long id) {
        log.info("ActionLog.getNotification start with id#" + id);

        NotificationEntity notificationEntity = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException(HttpStatus.NOT_FOUND.name(), "Notification not found"));

        log.info("ActionLog.getNotification end with id#" + id);
        return notificationMapper.mapToResponse(notificationEntity);
    }

    public List<NotificationResponse> getAllNotifications() {
        log.info("ActionLog.getAllNotifications start");

        List<NotificationResponse> notifications = notificationRepository.findAll().stream()
                .map(notificationMapper::mapToResponse)
                .collect(Collectors.toList());

        log.info("ActionLog.getAllNotifications end");
        return notifications;
    }
}
