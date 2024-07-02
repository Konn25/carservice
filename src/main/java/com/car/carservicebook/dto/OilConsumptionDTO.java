package com.car.carservicebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OilConsumptionDTO {

    Long id;

    Long carId;

    Double oilConsumption;

    String date;

    int kilometer;

}
