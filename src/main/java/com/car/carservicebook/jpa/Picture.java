package com.car.carservicebook.jpa;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import java.sql.Types;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "picture")
@Builder
@AllArgsConstructor
public class Picture {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String name;

    String type;

    @Lob
    @Column(length = 1000)
    //@JdbcTypeCode(Types.LONGVARBINARY)
    byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "car_id")
    Car car;
}
