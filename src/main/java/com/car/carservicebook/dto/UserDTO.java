package com.car.carservicebook.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    @JsonIgnore
    Long id;

    String nickName;

    String name;

    String email;

    String password;
}
