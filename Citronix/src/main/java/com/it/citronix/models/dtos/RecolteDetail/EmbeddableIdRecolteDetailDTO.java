package com.it.citronix.models.dtos.RecolteDetail;


import com.it.citronix.models.entities.Arbre;
import com.it.citronix.models.entities.Ferme;
import com.it.citronix.models.entities.Recolte;
import com.it.citronix.models.enums.Saison;
import com.it.citronix.validation.annotations.Exists;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddableIdRecolteDetailDTO {
    private Long arbreId;

    private Long recolteId;
}
