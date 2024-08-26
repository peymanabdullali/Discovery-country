package com.example.discovery_country.model.dto.response;

import com.example.discovery_country.dao.entity.ImageEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScenicSpotResponse {

    long id;
    String name;
    ImageResponseForRelations image;


}
