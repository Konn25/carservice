package com.car.carservicebook.service;

import com.car.carservicebook.jpa.RepairName;
import com.car.carservicebook.jpa.RepairNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepairNameService implements RepairNameServiceInterface{

    private final RepairNameRepository repairNameRepository;

    @Override
    public RepairName createNewRepairName(RepairName repairName) {
        return repairNameRepository.save(repairName);
    }

    @Override
    public List<RepairName> getAllRepairName() {
        return repairNameRepository.findAll();
    }
}
