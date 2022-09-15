package com.virtualminds.test.backend.shared.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaisseRequest {

    @NotNull(message = "Le libellé est obligatoire")
    @NotBlank(message = "Le libellé ne peut pas être vide")
    private String libelle ;

    @NotNull(message = "Les recettes sont obligatoires")
    @PositiveOrZero( message = "Les recettes doivent être positives")
    private double amountIn ;

    @NotNull(message = "Les dépenses sont obligatoires")
    @PositiveOrZero( message = "Les dépenses doivent être positives")
    private double amountOut ;

    @NotNull(message = "La date d'operation est obligatoire")
    private Date operationDate;
}
