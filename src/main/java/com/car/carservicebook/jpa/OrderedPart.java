package com.car.carservicebook.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orderedParts")
public class OrderedPart {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    Long carId;

    String companyName;

    String manufacturerName;

    Long partNameId;

    String carPartPictureName;

    String date;

    Double price;

}
