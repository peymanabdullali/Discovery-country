package com.example.discovery_country.dao.entity;

import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.enums.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "activities")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    Map<LangType, String> name;
    @Column(nullable = false)
    double price;

    long viewed;
    long likeCount;
    double averageRating;
    int ratingCount;

    Status activityStatus;

    String mapUrl;
    @Column(nullable = false)
    double latitude;
    @Column(nullable = false)
    double longitude;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    Map<LangType, String> description;
    boolean deleted = false;
    @Column(nullable = false)
    LocalDateTime startDate;

    @Column(nullable = false)
    LocalDateTime endDate;

    @Column(nullable = false)
    LocalDateTime registrationDeadline;

    @Column(nullable = false)
    String contact;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    Map<LangType, String> requirements;

    @Column(nullable = false)
    int numberOfPeople;


    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    List<ImageEntity> images;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn
    ActivityCategoryEntity activityCategory;

}