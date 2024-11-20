package com.it.citronix.models.dtos.Vente;


import com.it.citronix.models.entities.Recolte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVenteDTO {

    private Integer id;

    private LocalDate date;

    private Recolte recolte;

    private Double prixUnitaire;

    private Double quantiteDemande;

    private String clientName;

}
