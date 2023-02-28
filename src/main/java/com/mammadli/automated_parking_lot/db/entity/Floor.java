package com.mammadli.automated_parking_lot.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @ManyToOne()
//    @JoinColumn(name = "parking_lot_id",referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"floorList"}, allowSetters = true,allowGetters = true)
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "floor")
    @JsonIgnoreProperties(value = {"floor"}, allowSetters = true)
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
