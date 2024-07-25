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
@Table(name = "restaurants")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(nullable = false)
    String address;

    @Column(nullable = false)
    String contact;

    @ManyToOne
    @JoinColumn(nullable = false)
    RegionEntity region;

    @ManyToOne
    @JoinColumn(nullable = false)
    ImageEntity image;

    String mapUrl;
    String menuUrl;
    Long viewed;
    boolean status;
}
