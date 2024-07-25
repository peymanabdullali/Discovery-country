package com.example.discovery_country.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "activitycategories")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    RegionEntity region;

    @OneToMany(mappedBy = "activityCategory", cascade = CascadeType.ALL)
    List<ActivityEntity> activities;


    @Column(nullable = false)
  //  @Builder.Default
    Boolean deleted = false;
}
