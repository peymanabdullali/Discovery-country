package com.example.discovery_country.dao.entity;

import com.example.discovery_country.enums.LangType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    Map<LangType, String> name;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    Map<LangType, String> description;

    @Column(nullable = false)
    String address;

    @Column(nullable = false)
    String contact;

    @ManyToOne
    @JoinColumn(nullable = false)
    RegionEntity region;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<ImageEntity> images;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<ReviewEntity> reviews;
    double rating;
    String mapUrl;
    String menuUrl;

    

    long viewed;
    long likeCount;
    double averageRating;
    int ratingCount;

    boolean status;


}
