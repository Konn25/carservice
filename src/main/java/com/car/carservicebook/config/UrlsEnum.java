package com.car.carservicebook.config;

import lombok.Getter;

@Getter
public enum UrlsEnum {

    USER("/v1/user/data","/v1//user/update", "/v1/car/all/{userId}", "/v1/car/{id}",
            "/v1/car/new","/v1/car/update","/v1/car/picture/{id}", "/v1/car/refuel/{carId}", "/v1/car/refuel/new",
            "/v1/car/refuel/update", "/v1/car/refuel/delete/{id}", "/v1/car/repair/{carId}", "/v1/car/repair/new", "/v1/car/repair/delete/{id}",
            "/v1/car/repair/update/{id}", "/v1/repair/name/new", "/v1/repair/name"
    ),

    NOT_LOGGED_IN("/v1/authenticate","/v1/user/new",
            "/swagger-ui/**", "/v3/api-docs/**","/v2/api-docs/**", "/swagger-resources/**"
    );


    private final String[] urls;

    UrlsEnum(String... urls){
        this.urls = urls;
    }

}
