package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.NotificationEntity;
import com.example.discovery_country.model.dto.request.NotificationRequest;
import com.example.discovery_country.model.dto.response.NotificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers; // Bu import yalnız istifadə olunacaqsa saxlanılır

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL) // Və ya IGNORE seçin
public interface NotificationMapper {
    NotificationEntity mapToEntity(NotificationRequest request);

    NotificationResponse mapToResponse(NotificationEntity entity);

    void mapForUpdate(@MappingTarget NotificationEntity notificationEntity, NotificationRequest notificationRequest);
}
