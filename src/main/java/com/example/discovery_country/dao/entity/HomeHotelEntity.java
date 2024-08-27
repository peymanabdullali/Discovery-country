package com.example.discovery_country.dao.entity;

import com.example.discovery_country.enums.HomeHotelType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "homehotels")
public class HomeHotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(columnDefinition = "TEXT")
    String description;
    String address;
    String contact;
    String mapUrl;




    @Enumerated(EnumType.STRING)
    HomeHotelType type;
    long viewed;
    long likeCount;
    double averageRating;
    int ratingCount;
    boolean deleted;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    RegionEntity region;

    @OneToMany(mappedBy = "homeHotel", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    Set<RoomEntity> rooms;

    @OneToMany(mappedBy = "homeHotel", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    List<ImageEntity> images;

    @OneToMany(mappedBy = "homeHotel", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    Set<ReviewEntity> reviews;


}
