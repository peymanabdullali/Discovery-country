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
@Table(name = "scenicspots")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScenicSpotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    RegionEntity region;

    @OneToMany(mappedBy = "scenicSpot", cascade = CascadeType.ALL)
    List<ImageEntity> images;

    Long viewed;

}
