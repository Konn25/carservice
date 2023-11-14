package com.car.carservicebook.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RepairNameDTO {

    @JsonIgnore
    Long id;

    String repair_name;


}
