package com.example.discovery_country.model.dto.request;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityRequest {



    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    double price;

    String mapUrl;

    @Column(nullable = false)
    double latitude;

    @Column(nullable = false)
    double longitude;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(nullable = false)
    LocalDateTime startDate;

    @Column(nullable = false)
    LocalDateTime endDate;

    @Column(nullable = false)
    LocalDateTime registrationDeadline;

    @Column(nullable = false)
    String contact;

    @Column(columnDefinition = "TEXT")
    String requirements;

    @Column(nullable = false)
    Integer numberOfPeople;

    List<Long> imageIds;
    Long activityCategoryId;

}



