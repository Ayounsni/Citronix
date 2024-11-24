package com.it.citronix.models.dtos.Recolte;


import com.it.citronix.models.enums.Saison;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddableRecolteDTO {

    private Long id;

    private Saison saison;

    private LocalDate dateRecolte;

    private Double quantiteTotal;

}
