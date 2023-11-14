package com.car.carservicebook.service;

import com.car.carservicebook.jpa.Refueling;
import com.car.carservicebook.jpa.RefuelingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefuelingService implements RefuelingServiceInterface{

    private final RefuelingRepository refuelingRepository;

    @Override
    public List<Refueling> getRefuelingByCarId(Long carId) {
        return refuelingRepository.findRefuelingByCarId(carId);
    }

    @Override
    public Refueling createNewRefueling(Refueling refueling) {
        return refuelingRepository.save(refueling);
    }

    @Override
    public Refueling updateRefueling(Refueling refueling) {
        return refuelingRepository.save(refueling);
    }

    @Override
    public void deleteRefueling(Long id) {

        Optional<Refueling> foundRefuel = refuelingRepository.findById(id);

        if(foundRefuel.isPresent() && foundRefuel.get().getId()>0){
            refuelingRepository.deleteById(id);
        }
    }
}
