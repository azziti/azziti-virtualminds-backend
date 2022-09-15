package com.virtualminds.test.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Caisse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caisseID ;

    @NotNull(message = "Le libellé est obligatoire")
    @NotBlank(message = "Le libellé ne peut pas être vide")
    private String libelle ;


    @NotNull(message = "Les recettes sont obligatoires")
    @PositiveOrZero( message = "Les recettes doivent être positives")
    @Column(name="recettes")
    private double amountIn = 0;

    @NotNull(message = "Les dépenses sont obligatoires")
    @PositiveOrZero( message = "Les dépenses doivent être positives")
    @Column(name="depenses")
    private double amountOut = 0;

    @NotNull(message = "La date d'operation est obligatoire")
    private Date operationDate;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    public Caisse(String libelle, double amountIn, double amountOut, Date operationDate) {
        this.libelle = libelle;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
        this.operationDate = operationDate;
    }
}
