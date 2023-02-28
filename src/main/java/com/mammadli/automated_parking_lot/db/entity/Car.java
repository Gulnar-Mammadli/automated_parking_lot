package com.mammadli.automated_parking_lot.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @ManyToOne()
//    @JoinColumn(name = "floor_id",referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"cars"}, allowSetters = true,allowGetters = true)
    private Floor floor;

    private LocalDateTime parkingTime;

    private LocalDateTime unparkingTime;
}
