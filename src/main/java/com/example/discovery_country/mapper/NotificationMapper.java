package com.example.discovery_country.mapper;

import com.example.discovery_country.dao.entity.NotificationEntity;
import com.example.discovery_country.model.dto.request.NotificationRequest;
import com.example.discovery_country.model.dto.response.NotificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface NotificationMapper {
        NotificationEntity mapToEntity(NotificationRequest request);

        NotificationResponse mapToResponse(NotificationEntity entity);

        void mapForUpdate(@MappingTarget NotificationEntity notificationEntity, NotificationRequest notificationRequest);

    }


