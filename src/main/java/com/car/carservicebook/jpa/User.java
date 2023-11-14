package com.car.carservicebook.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
//@ToString
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String nickName;

    String name;

    String email;

    String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Car> cars;
}
