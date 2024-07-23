package com.example.discovery_country.dao.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "zones")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ZoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    List<RegionEntity> regions;
}
