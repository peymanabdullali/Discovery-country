package com.example.discovery_country.model.dto.criteria;

import com.example.discovery_country.enums.LangType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ActivityCriteriaRequest {

    String name;
    double priceGreaterThan;
    double priceLessThan;
    LocalDate startDate;
    LocalDate endDate;
    LangType key;
}
