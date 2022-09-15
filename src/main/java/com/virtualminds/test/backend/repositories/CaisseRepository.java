package com.virtualminds.test.backend.repositories;

import com.virtualminds.test.backend.entities.Caisse;
import com.virtualminds.test.backend.shared.responses.CaisseResponse;
import com.virtualminds.test.backend.shared.responses.CaisseResponseInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CaisseRepository extends JpaRepository<Caisse, Long> {
    // from start periode to end periode
    @Query(nativeQuery = true,
            value = "select c.caisseID as caisseID, c.libelle as libelle, c.recettes as amountIn, c.depenses as amountOut, c.operation_date as operationDate, " +
                    "(select SUM(recettes) - SUM(depenses) from caisse WHERE (operation_date <  c.operation_date) OR ( operation_date = c.operation_date and created_at <=  c.created_at  )) as solde " +
                    "from caisse c where (c.operation_date <= :end and c.operation_date >= :start ) ORDER BY operation_date asc, created_at asc;")
    List<CaisseResponseInterface> findCaissesBetweenStartAndEnd(@Param("start") Date start, @Param("end") Date end);


}
