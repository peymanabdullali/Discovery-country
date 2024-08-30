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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "activitycategories")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    Map<LangType, String> name;
    @Column(nullable = false)
    boolean deleted;

    @OneToMany(mappedBy = "activityCategory", cascade = CascadeType.MERGE)
    List<ActivityEntity> activities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "region_act_category",joinColumns = @JoinColumn(name="activity_category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "region_id", referencedColumnName = "id")
    )
    List <RegionEntity> regions;







}
