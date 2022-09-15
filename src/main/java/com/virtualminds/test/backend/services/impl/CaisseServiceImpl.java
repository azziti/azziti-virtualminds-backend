package com.virtualminds.test.backend.services.impl;

import com.virtualminds.test.backend.entities.Caisse;
import com.virtualminds.test.backend.repositories.CaisseRepository;
import com.virtualminds.test.backend.services.CaisseService;
import com.virtualminds.test.backend.shared.responses.CaisseResponse;
import com.virtualminds.test.backend.shared.responses.CaisseResponseInterface;
import com.virtualminds.test.backend.utils.error_managment.exceptions.NoSuchCaisseExistsException;
import com.virtualminds.test.backend.utils.heplers.CSVHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CaisseServiceImpl implements CaisseService {

    @Autowired
    private CaisseRepository caisseRepository;

    @Override
    public CaisseResponse getCaisseById(Long id) throws NoSuchCaisseExistsException, InvocationTargetException, IllegalAccessException {

        Caisse caisse = caisseRepository.findById(id).orElseThrow(() -> new NoSuchCaisseExistsException("il n'existe aucune caisse portant l'id : " + id));

        CaisseResponse caisseResponse = new CaisseResponse();
        BeanUtils.copyProperties(caisseResponse, caisse);

        return caisseResponse;
    }

    @Override
    public Caisse insert(Caisse caisse) throws ConstraintViolationException {
        return this.caisseRepository.save(caisse);
    }

    @Override
    public void updateCaisse(Long id, Caisse caisse) throws NoSuchCaisseExistsException {

        Caisse caisseFromDB = caisseRepository.findById(id).orElseThrow(
                () -> new NoSuchCaisseExistsException("il n'existe aucune caisse portant l'id : \"+id")
        );

        caisseFromDB.setAmountIn(caisse.getAmountIn());
        caisseFromDB.setAmountOut(caisse.getAmountOut());
        caisseFromDB.setLibelle(caisse.getLibelle());
        caisseFromDB.setOperationDate(caisse.getOperationDate());

        caisseRepository.save(caisseFromDB);
    }

    @Override
    public void deleteCaisse(Long id) {
        caisseRepository.deleteById(id);
    }


    // get caisses between two date
    @Override
    public List<CaisseResponseInterface> getCaissesByPeriode(Date start, Date end) {

        return caisseRepository.findCaissesBetweenStartAndEnd(start, end);
    }

    @Override
    public List<Caisse> findAllCaisses() {
        return caisseRepository.findAll();
    }

    //get file save caisses to the database
    @Override
    public void saveFromFile(MultipartFile file) throws IOException {
        try {
            List<Caisse> caisses = CSVHelper.csvToCaisses(file.getInputStream());
            caisseRepository.saveAll(caisses);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

}
