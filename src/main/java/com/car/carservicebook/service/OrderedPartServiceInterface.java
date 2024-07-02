package com.car.carservicebook.service;

import com.car.carservicebook.jpa.OrderedPart;

import java.util.List;

public interface OrderedPartServiceInterface {

    List<OrderedPart> getAllOrderedPartsByCarId(Long car_id);

    OrderedPart createNewOrderedPart(OrderedPart orderedPart);

    OrderedPart updateNewOrderedPart(OrderedPart orderedPart);

    void deleteOrderedPartById(Long id);


}
