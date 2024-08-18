package com.example.discovery_country.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    Boolean checkStatus;
    boolean status;

    @ManyToOne
    @JoinColumn(nullable = false)
    RegionEntity region;

    @OneToMany(mappedBy = "scenicSpot", cascade = CascadeType.ALL)
    List<ImageEntity> images;
    @OneToMany(mappedBy = "scenicSpot", cascade = CascadeType.ALL)
    List<ReviewEntity> reviews;
    long viewed=0;
    long likeCount=0;
    Double averageRating = 0.0;
    long ratingCount = 0;
}
