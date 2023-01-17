package com.mammadli.automated_parking_lot.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "floor")
public class Floor {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(
            name = "uuid2",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column( nullable = false,columnDefinition = "VARCHAR(255)")
    private String id;

    private int ceilingHeight;
    private int weightCapacity;
    private int maxCapacity;

    @ManyToOne
    private ParkingLot parkingLot;
    @OneToMany
    private List<Car> cars;

    public boolean spaceExists() {
        int sumOfWeight = 0;
        int numberOfCars = cars.size();

        if(numberOfCars >= maxCapacity){
            return false;
        }

        for(Car car: this.cars){
            sumOfWeight += car.getWeight();
        }

        if(sumOfWeight >= weightCapacity){
            return false;
        }

        return true;
    }
}
