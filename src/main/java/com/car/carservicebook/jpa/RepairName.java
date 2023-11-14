package com.car.carservicebook.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "repairName")
public class RepairName {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String repairName;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "repair_id")
    List<Repair> repair;
}
