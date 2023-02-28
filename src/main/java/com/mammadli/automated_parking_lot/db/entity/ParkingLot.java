package com.mammadli.automated_parking_lot.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "parking_lot")
public class ParkingLot {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(
            name = "uuid2",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column( nullable = false,columnDefinition = "VARCHAR(255)")

    private String id;

    @JsonIgnore
    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Floor> floorList;

    private BigDecimal price;
}
