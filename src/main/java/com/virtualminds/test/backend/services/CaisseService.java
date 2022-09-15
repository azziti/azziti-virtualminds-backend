package com.virtualminds.test.backend.services;

import com.virtualminds.test.backend.entities.Caisse;
import com.virtualminds.test.backend.shared.responses.CaisseResponse;
import com.virtualminds.test.backend.shared.responses.CaisseResponseInterface;
import com.virtualminds.test.backend.utils.error_managment.exceptions.NoSuchCaisseExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

public interface CaisseService {
    public CaisseResponse getCaisseById(Long id) throws NoSuchCaisseExistsException, InvocationTargetException, IllegalAccessException;
    public Caisse insert(Caisse caisse) throws ConstraintViolationException;
    public void updateCaisse(Long id ,Caisse caisse) throws NoSuchCaisseExistsException;
    public void deleteCaisse(Long id);
    public List<CaisseResponseInterface> getCaissesByPeriode(Date start, Date end);
    public List<Caisse> findAllCaisses();
    public void saveFromFile(MultipartFile file) throws IOException;
}
