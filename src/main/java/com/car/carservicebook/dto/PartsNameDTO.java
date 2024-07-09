package com.car.carservicebook.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartsNameDTO {

    @JsonIgnore
    Long id;

    String name;

    String pictureURL;

}
