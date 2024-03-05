package com.car.carservicebook.controller;

import com.car.carservicebook.dto.RepairNameDTO;
import com.car.carservicebook.jpa.RepairName;
import com.car.carservicebook.jpa.RepairNameRepository;
import com.car.carservicebook.jpa.RepairRepository;
import com.car.carservicebook.service.RepairNameService;
import com.car.carservicebook.service.RepairService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@MockitoSettings(strictness = Strictness.LENIENT)
class RepairNameControllerTest {

    @Mock
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RepairNameRepository repairNameRepository;

    @Mock
    private RepairNameService repairNameService;

    @Mock
    private RepairRepository repairRepository;

    @Mock
    private RepairService repairService;

    @InjectMocks
    private RepairNameController repairNameController;

    private final RepairName repairName = new RepairName();

    private final RepairNameDTO repairNameDTO = new RepairNameDTO();


    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(repairNameController).build();

        repairService = new RepairService(repairRepository);

        repairNameService = new RepairNameService(repairNameRepository);

        repairNameController = new RepairNameController(modelMapper, repairNameService, repairService);

        repairName.setId(1L);
        repairName.setRepairName("Oil change");
        repairName.setRepair(new ArrayList<>());

        repairNameDTO.setId(1L);
        repairNameDTO.setRepair_name("Oil change");

    }

    @Test
    void getAllRepairName() throws Exception {

        //GIVEN
        repairNameRepository.save(repairName);

        //WHEN
        when(repairNameService.getAllRepairName()).thenReturn(List.of(repairName));

        MvcResult result = mockMvc.perform(get("/v1/repair/name")
                                                   .contentType(MediaType.APPLICATION_JSON)).andReturn();

        //THEN
        assertEquals(200, result.getResponse().getStatus());

    }

    @Test
    void createNewRepairName() {

        //GIVEN
        given(modelMapper.map(repairName, RepairNameDTO.class)).willReturn(repairNameDTO);
        given(modelMapper.map(repairNameDTO, RepairName.class)).willReturn(repairName);
        given(repairNameRepository.findAll()).willReturn(List.of(repairName));
        given(repairNameService.createNewRepairName(repairName)).willReturn(repairName);

        //WHEN
        ResponseEntity<String> actual = repairNameController.createNewRepairName(repairNameDTO);

        //THEN
        assertEquals(HttpStatus.CREATED, actual.getStatusCode());

    }
}