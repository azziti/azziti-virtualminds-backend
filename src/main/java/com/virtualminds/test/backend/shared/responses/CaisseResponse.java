package com.virtualminds.test.backend.shared.responses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaisseResponse {


    private Long caisseID;
    private String libelle ;
    private double amountIn ;
    private double amountOut ;
    private double solde ;
    private Date operationDate;
//    private Date createdAt;


}
