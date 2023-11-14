package com.car.carservicebook.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String name;

    @ManyToOne
    @JoinColumn(name = "car_id")
    Car car;
}
