package com.car.carservicebook.service;

import com.car.carservicebook.jpa.OrderedPart;
import com.car.carservicebook.jpa.OrderedPartRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderedPartService implements OrderedPartServiceInterface {

    @Autowired
    private final OrderedPartRepository orderedPartRepository;

    @Override
    public List<OrderedPart> getAllOrderedPartsByCarId(Long car_id) {
        return orderedPartRepository.findOrderedPartByCarId(car_id);
    }

    @Override
    public OrderedPart createNewOrderedPart(OrderedPart orderedPart) {
        return orderedPartRepository.save(orderedPart);
    }

    @Override
    public OrderedPart updateNewOrderedPart(OrderedPart orderedPart) {
        Optional<OrderedPart> foundPart = orderedPartRepository.findOrderedPartById(orderedPart.getId());

        foundPart.ifPresent(value -> orderedPart.setId(value.getId()));
        return orderedPartRepository.save(orderedPart);
    }

    @Override
    @Transactional
    public void deleteOrderedPartById(Long id) {
        Optional<OrderedPart> foundPart = orderedPartRepository.findOrderedPartById(id);

        if (foundPart.isPresent() && foundPart.get().getId() > 0) {
            orderedPartRepository.deleteById(id);
        }
    }
}
