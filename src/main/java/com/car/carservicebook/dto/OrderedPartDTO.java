package com.car.carservicebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderedPartDTO {

    Long id;

    Long carId;

    String companyName;

    String manufacturerName;

    Long partNameId;

    String carPartPictureName;

    String date;

    Double price;


}
