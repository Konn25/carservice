package com.car.carservicebook.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RefuelingDTO {

    @JsonIgnore
    Long id;

    Long car_id;

    int kilometer;

    double fuelQuantity;

    int price;

    String date;

}
