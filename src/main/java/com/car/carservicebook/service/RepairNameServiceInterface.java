package com.car.carservicebook.service;

import com.car.carservicebook.jpa.RepairName;

import java.util.List;

public interface RepairNameServiceInterface {

    RepairName createNewRepairName(RepairName repairName);

    List<RepairName> getAllRepairName();

}
