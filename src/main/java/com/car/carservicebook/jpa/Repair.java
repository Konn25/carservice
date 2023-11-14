package com.car.carservicebook.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "repair")
public class Repair {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    int price;

    String date;

    Long repair_id;

    //@OneToMany(mappedBy = "repair" , cascade = CascadeType.ALL)
    //List<RepairName> repairName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "car_id")
    Car car;

}
