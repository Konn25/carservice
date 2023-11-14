package com.car.carservicebook.jpa;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "car")
//@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String manufacturer;

    String type;

    Double motor;

    String fuel;

    int kilometer;

    int year;

    int price;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car", cascade = CascadeType.ALL)
    List<Refueling> refuelingList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car", cascade = CascadeType.ALL)
    List<Repair> repairList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car", cascade = CascadeType.ALL)
    List<Picture> pictureList;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;
}
