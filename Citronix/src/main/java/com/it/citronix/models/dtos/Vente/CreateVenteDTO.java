package com.it.citronix.models.dtos.Vente;


import com.it.citronix.models.entities.Recolte;
import com.it.citronix.models.enums.Saison;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVenteDTO {

    @NotNull
    private LocalDate date;

    @NotNull
    private Long recolteId;

    @NotNull
    private Double prixUnitaire;

    @NotNull
    private Double quantiteDemande;

    @NotBlank
    private String clientName;

}
