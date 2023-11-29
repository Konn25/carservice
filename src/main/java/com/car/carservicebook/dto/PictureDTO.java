package com.car.carservicebook.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PictureDTO {

    @JsonIgnore
    Long id;

    Long car_id;

    String name;

    String type;

    byte[] imageData;

}
