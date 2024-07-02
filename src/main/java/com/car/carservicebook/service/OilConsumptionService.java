package com.car.carservicebook.service;

import com.car.carservicebook.jpa.OilConsumption;
import com.car.carservicebook.jpa.OilConsumptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OilConsumptionService implements OilConsumptionServiceInterface {

    @Autowired
    private final OilConsumptionRepository oilConsumptionRepository;

    @Override
    public List<OilConsumption> getAllOilConsumptionByCarId(Long car_id) {
        return oilConsumptionRepository.findOilConsumptionByCarId(car_id);
    }

    @Override
    public OilConsumption createNewOilConsumption(OilConsumption oilConsumption) {
        return oilConsumptionRepository.save(oilConsumption);
    }

    @Override
    public OilConsumption updateOilConsumption(OilConsumption oilConsumption) {

        Optional<OilConsumption> foundOilConsumption = oilConsumptionRepository.findOilConsumptionById(
                oilConsumption.getId());

        foundOilConsumption.ifPresent(value -> oilConsumption.setId(value.getId()));

        return oilConsumptionRepository.save(oilConsumption);
    }

    @Override
    @Transactional
    public void deleteOilConsumptionById(Long id) {

        Optional<OilConsumption> foundOilConsumption = oilConsumptionRepository.findOilConsumptionById(id);

        if (foundOilConsumption.isPresent() && foundOilConsumption.get().getId() > 0) {
            oilConsumptionRepository.deleteById(id);
        }

    }
}
