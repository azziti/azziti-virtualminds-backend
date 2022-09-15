package com.virtualminds.test.backend.shared.responses;

import javax.validation.constraints.NotNull;
import java.util.Date;

public interface CaisseResponseInterface {
    Long getCaisseID();
    String getLibelle() ;
    double getAmountIn() ;
    double getAmountOut() ;
    double getSolde() ;
    Date getOperationDate();
//    Date getCreatedAt();
}
