package com.example.discovery_country.dao.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@Table(name = "activitycategories")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    RegionEntity region;

    @OneToMany(mappedBy = "activityCategory", cascade = CascadeType.ALL)
    List<ActivityEntity> activities;
}
