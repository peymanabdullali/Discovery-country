package com.example.discovery_country.model.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityResponseFindById {


    String name;

    double price;

    String mapUrl;

    double latitude;

    double longitude;

    String description;

    LocalDateTime startDate;

    LocalDateTime endDate;

    LocalDateTime registrationDeadline;

    String contact;

    String requirements;

    Integer numberOfPeople;

    List<ImageResponseForRelations> images;
//    ActivityCategoryResponseForActivity activityCategory;

}
