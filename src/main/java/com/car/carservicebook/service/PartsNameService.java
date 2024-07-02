package com.car.carservicebook.service;

import com.car.carservicebook.jpa.PartsName;
import com.car.carservicebook.jpa.PartsNameRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PartsNameService implements PartsNameServiceInterface{

    @Autowired
    private final PartsNameRepository partsNameRepository;

    @Override
    public PartsName getPartsNameById(Long id) {
        return partsNameRepository.findPartsNameById(id);
    }

    @Override
    public List<PartsName> getAllName() {
        return partsNameRepository.findAll();
    }
}
