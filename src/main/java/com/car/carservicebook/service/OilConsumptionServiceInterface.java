package com.car.carservicebook.service;

import com.car.carservicebook.jpa.OilConsumption;

import java.util.List;


public interface OilConsumptionServiceInterface {

    List<OilConsumption> getAllOilConsumptionByCarId(Long car_id);

    OilConsumption createNewOilConsumption(OilConsumption oilConsumption);

    OilConsumption updateOilConsumption(OilConsumption oilConsumption);

    void deleteOilConsumptionById(Long id);

}
