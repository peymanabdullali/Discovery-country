package com.example.discovery_country.model.dto.response;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityCategoryResponse {


    @Column(nullable = false)
    String name;

    //  RegionEntity region;
    List<Long>  regionIds;
    //List<ActivityResponse> activities;
    List<Long>  activityIds;
}
