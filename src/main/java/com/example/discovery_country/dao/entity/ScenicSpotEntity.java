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
    @Column(columnDefinition = "indicates verification by admin")
    boolean checkStatus;
    @Column(columnDefinition = "checks that it has not been deleted")
    boolean status;

    @ManyToOne
    @JoinColumn(nullable = false)
    RegionEntity region;

    @OneToMany(mappedBy = "scenicSpot", cascade = CascadeType.ALL)
    List<ImageEntity> images;
    @OneToMany(mappedBy = "scenicSpot", cascade = CascadeType.ALL)
    List<ReviewEntity> reviews;
    Long viewed;

}
