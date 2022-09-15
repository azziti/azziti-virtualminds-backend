package com.virtualminds.test.backend.repositories;

import com.virtualminds.test.backend.entities.Caisse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CaisseRepositoryTest {

    @Autowired
    private CaisseRepository caisseRepository;

    @Test
    public void saveCaisse() throws ParseException {

        String date_string = "20-09-2022";
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //Parsing the given String to Date object
        Date date = formatter.parse(date_string);

        Caisse caisse = Caisse.builder()
                .amountIn(150)
                .amountOut(50)
                .libelle("Operation de test")
                .operationDate(date)
                .build();

        caisseRepository.save(caisse);
    }

    @Test
    public void printAllCaisses(){
        List<Caisse> caissesList = caisseRepository.findAll();
        System.out.println("liste des aisses : " + caissesList);
    }



}