package com.car.carservicebook.service;

import com.car.carservicebook.jpa.Car;
import com.car.carservicebook.jpa.Repair;
import com.car.carservicebook.jpa.RepairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RepairService implements RepairServiceInterface{

    private final RepairRepository repairRepository;

    @Override
    public List<Repair> getAllRepairByCarId(Long carId) {
        return repairRepository.findRepairByCarId(carId);
    }

    @Override
    public Repair createNewRepair(Repair repair) {
        return repairRepository.save(repair);
    }

    @Override
    public void deleteRepair(Long id) {

        Optional<Repair> foundRepair = repairRepository.findById(id);

        if(foundRepair.isPresent() && foundRepair.get().getId()>0){
            repairRepository.deleteById(id);
        }
    }

    @Override
    public Repair updateRepair(Repair repair) {

        Optional<Repair> foundCar = repairRepository.findById(repair.getId());

        foundCar.ifPresent(value -> repair.setId(value.getId()));

        return repairRepository.save(repair);
    }

    @Override
    public Repair getRepairById(Long id) {
        return repairRepository.findRepairById(id);
    }
}
