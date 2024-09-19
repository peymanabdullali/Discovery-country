package com.example.discovery_country.model.dto.criteria;

import com.example.discovery_country.dao.entity.auth.User;
import com.example.discovery_country.enums.LangType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RoomReservationCriteriaRequest {
    String name;
    LocalDate entryDate;
    boolean status;
    boolean deleted;
    LocalDate exitDate;
    LangType langType;
}
