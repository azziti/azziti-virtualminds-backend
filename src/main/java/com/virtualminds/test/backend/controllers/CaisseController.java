package com.virtualminds.test.backend.controllers;

import com.virtualminds.test.backend.entities.Caisse;
import com.virtualminds.test.backend.services.CaisseService;
import com.virtualminds.test.backend.shared.requests.CaisseRequest;
import com.virtualminds.test.backend.shared.responses.CaisseResponseInterface;
import com.virtualminds.test.backend.utils.error_managment.exceptions.NotCSVFileException;
import com.virtualminds.test.backend.utils.heplers.CSVHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("caisse")
public class CaisseController {

    @Autowired
    private CaisseService caisseService;


    // get caisses by periode
    @GetMapping("/periode")
    public List<CaisseResponseInterface> getCaissesByPeriode(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start, @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) {

        return caisseService.getCaissesByPeriode(start, end);
    }

    // get all caisses
    @GetMapping()
    public ResponseEntity<List<Caisse>> getAllCaisses() {
        List<Caisse> caisse = caisseService.findAllCaisses();
        return new ResponseEntity<>(caisse, HttpStatus.OK);
    }

    @GetMapping({"/{caisseId}"})
    public ResponseEntity<Caisse> getCaisseById(@PathVariable("caisseId") Long caisseId) throws InvocationTargetException, IllegalAccessException {

        return new ResponseEntity(caisseService.getCaisseById(caisseId), HttpStatus.OK);
    }

    // new caisse
    @PostMapping
    public ResponseEntity<Caisse> saveCaisse(@RequestBody @Valid CaisseRequest caisseRequest) throws InvocationTargetException, IllegalAccessException {
        Caisse caisse = new Caisse();
        BeanUtils.copyProperties(caisse, caisseRequest);
        Caisse _caisse = caisseService.insert(caisse);
        return new ResponseEntity<>(_caisse, HttpStatus.CREATED);
    }

    //get caisse by id
    @PutMapping("/{caisseId}")
    public ResponseEntity<Caisse> updateCaisse(@PathVariable("caisseId") Long caisseId, @RequestBody @Valid CaisseRequest caisseRequest) throws InvocationTargetException, IllegalAccessException {

        Caisse caisse = new Caisse();
        BeanUtils.copyProperties(caisse, caisseRequest);

        System.out.println();

        caisseService.updateCaisse(caisseId, caisse);
        return new ResponseEntity(caisseService.getCaisseById(caisseId), HttpStatus.OK);
    }

    // upload csv file
    @PostMapping("/csv/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            caisseService.saveFromFile(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return new ResponseEntity<>(message, HttpStatus.CREATED);

//            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            message = "provide a valid csv file";
            throw new NotCSVFileException(message);
        }

    }

    //delete caisse
    @DeleteMapping({"/{caisseId}"})
    public ResponseEntity<Caisse> deleteCaisse(@PathVariable("caisseId") Long caisseID) {
        caisseService.deleteCaisse(caisseID);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
