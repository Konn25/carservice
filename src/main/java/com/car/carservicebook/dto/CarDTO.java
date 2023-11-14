package com.car.carservicebook.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CarDTO {

    //@JsonIgnore
    Long id;

    Long user_id;

    String manufacturer;

    String type;

    Double motor;

    String fuel;

    int kilometer;

    int year;

    int price;

}
