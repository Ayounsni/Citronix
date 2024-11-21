package com.it.citronix.models.dtos.Vente;


import com.it.citronix.models.entities.Recolte;
import com.it.citronix.validation.annotations.Exists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVenteDTO {

    private LocalDate date;

    @Exists(entity = Recolte.class, message = "Cette récolte n'existe pas.")
    private Long recolteId;

    private Double prixUnitaire;

    private Double quantiteDemande;

    private String clientName;

}
