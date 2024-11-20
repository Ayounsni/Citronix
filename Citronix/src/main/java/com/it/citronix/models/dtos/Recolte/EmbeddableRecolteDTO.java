package com.it.citronix.models.dtos.Recolte;


import com.it.citronix.models.entities.RecolteDetail;
import com.it.citronix.models.entities.Vente;
import com.it.citronix.models.enums.Saison;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddableRecolteDTO {

    private Integer id;

    private Saison saison;

    private LocalDate dateRecolte;

}
