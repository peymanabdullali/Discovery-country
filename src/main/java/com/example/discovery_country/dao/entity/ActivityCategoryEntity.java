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

    @Column(nullable = false)
    Boolean deleted = false;

    @OneToMany(mappedBy = "activityCategory", cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    List<ActivityEntity> activities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "region_actCategory",joinColumns = @JoinColumn(name="activity_category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "region_id", referencedColumnName = "id")
    )
    List <RegionEntity> regions;







}
