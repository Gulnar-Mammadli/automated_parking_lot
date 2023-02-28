package com.mammadli.automated_parking_lot.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(
            name = "uuid2",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column( nullable = false,columnDefinition = "VARCHAR(255)")
    private  String id;

    private int height;

    private int weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id",referencedColumnName = "id")
    private Floor floor;

    private LocalDateTime parkingTime;

    private LocalDateTime unparkingTime;
}
