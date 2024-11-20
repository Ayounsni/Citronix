package com.it.citronix.models.dtos.Vente;


import com.it.citronix.models.enums.Saison;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVenteDTO {

    private LocalDate date;

    private Long recolteId;

    private Double prixUnitaire;

    private Double quantiteDemande;

    private String clientName;

}
