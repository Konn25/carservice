package com.car.carservicebook.service;

import com.car.carservicebook.jpa.Refueling;
import com.car.carservicebook.jpa.Repair;

import java.util.List;

public interface RepairServiceInterface {

    List<Repair> getAllRepairByCarId(Long carId);

    Repair createNewRepair(Repair repair);

    void deleteRepair(Long id);

    Repair updateRepair(Repair repair);

    Repair getRepairById(Long id);

}
