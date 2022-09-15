package com.virtualminds.test.backend.controllers;

import com.virtualminds.test.backend.entities.Caisse;
import com.virtualminds.test.backend.services.CaisseService;
import com.virtualminds.test.backend.shared.responses.CaisseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



@WebMvcTest(CaisseController.class)
@AutoConfigureMockMvc
class CaisseControllerTest {

//    @Autowired
    private MockMvc mockMvc ;
    @MockBean
    private CaisseService caisseService;
    private CaisseResponse caisseResponse;

    @BeforeEach
    void setUp() throws ParseException {

        String date_string = "20-09-2022";
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //Parsing the given String to Date object
        Date date = formatter.parse(date_string);

        caisseResponse = CaisseResponse.builder()
                .amountIn(150)
                .amountOut(50)
                .libelle("Operation de test")
                .operationDate(date)
                .solde(50)
                .build();

    }

        @Test
    void fetchCaissseById() throws Exception {
        Mockito.when(caisseService.getCaisseById(1L))
                .thenReturn(caisseResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/caisse/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

}