package com.example.discovery_country.dao.entity;

import com.example.discovery_country.enums.LangType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scenicspots")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScenicSpotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    Map<LangType, String> name;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    Map<LangType, String> description;
    boolean checkStatus;
    boolean status;

    @ManyToOne
    @JoinColumn(nullable = false)
    RegionEntity region;

    @OneToMany(mappedBy = "scenicSpot", cascade = CascadeType.ALL)
    List<ImageEntity> images;
    @OneToMany(mappedBy = "scenicSpot", cascade = CascadeType.ALL)
    List<ReviewEntity> reviews;

    long viewed;
    long likeCount;
    double averageRating;
    int ratingCount;
}
