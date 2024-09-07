package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.enums.LangType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.annotation.Before;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityRequest {



    @NotEmpty(message = "Name can't be empty")
    Map<LangType, String> name;

    @Min(value = 0, message = "Price must be positive")
    double price;

    @Pattern(regexp = "https?://.*", message = "Map URL should be a valid URL")
    String mapUrl;

    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    double latitude;

    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    double longitude;

    @NotEmpty(message = "Description can't be empty")
    Map<LangType, String> description;

    @FutureOrPresent(message = "Start date must be in the future or present")
    LocalDateTime startDate;

    @Future(message = "End date must be in the future")
    LocalDateTime endDate;

    LocalDateTime registrationDeadline;

    @Pattern(regexp = "^\\+994[0-9]{9}$", message = "Contact number must be a valid Azerbaijani phone number starting with +994")
    @NotBlank(message = "Contact can't be blank")
    String contact;

    @NotEmpty(message = "Requirements can't be empty")
    Map<LangType, String> requirements;

    @Min(value = 1, message = "Number of people must be at least 1")
    Integer numberOfPeople;

    @NotEmpty(message = "Image IDs can't be empty")
    List<Long> imageIds;

    @NotNull(message = "Activity Category ID can't be null")
    Long activityCategoryId;

}



