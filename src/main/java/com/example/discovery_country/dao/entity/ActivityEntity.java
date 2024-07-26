package com.example.discovery_country.dao.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "activities")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    double price;

    String mapUrl;
    @Column(nullable = false)
    double latitude;
    @Column(nullable = false)
    double longitude;
    @Column(columnDefinition = "TEXT")
    String description;

    @Column(nullable = false)
    LocalDateTime startDate;

    @Column(nullable = false)
    LocalDateTime endDate;

    @Column(nullable = false)
    LocalDateTime registrationDeadline;

    @Column(nullable = false)
    String contact;

    @Column(columnDefinition = "TEXT")
    String requirements;

    @Column(nullable = false)
    Integer numberOfPeople;

    @ManyToOne
    @JoinColumn(nullable = false)
    ImageEntity image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    ActivityCategoryEntity activityCategory;

    Boolean deleted;
}



