package com.example.discovery_country.dao.entity;

import com.example.discovery_country.dao.entity.auth.User;
import com.example.discovery_country.enums.LangType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Table(name = "room_reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    Map<LangType, String> name;
    LocalDate entryDate;
    LocalDate exitDate;
    byte totalDay;
    double totalAmount;
    byte numberOfGuests;
    boolean status;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    RoomEntity room;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    User user;
    @PrePersist
    public void setStatusTrue() {
        status = true;
    }


}
