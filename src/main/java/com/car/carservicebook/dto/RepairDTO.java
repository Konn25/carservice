package com.car.carservicebook.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RepairDTO {

    @JsonIgnore
    Long id;

    Long car_id;

    Long repair_id;

    int price;

    String date;

}
