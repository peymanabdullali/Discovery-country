package com.example.discovery_country.dao.entity;

import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    Map<LangType, String> name;
    double price;
    @Column(columnDefinition = "TEXT")
    String amenities;
    byte roomNumber;
    byte roomCount;
    @Enumerated(EnumType.STRING)
    RoomType roomType;
    boolean available;
    boolean deleted;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    HomeHotelEntity homeHotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    List<ImageEntity> images;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    List<RoomReservationEntity> roomReservations;

    @PrePersist
    public void setAvailableTrue() {
        available = true;
    }
}
