package com.car.carservicebook.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "refueling")
public class Refueling {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    int kilometer;

    double fuelQuantity;

    int price;

    String date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "car_id")
    Car car;

}
