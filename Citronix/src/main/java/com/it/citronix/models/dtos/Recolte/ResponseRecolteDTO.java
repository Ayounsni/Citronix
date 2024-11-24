package com.it.citronix.models.dtos.Recolte;


import com.it.citronix.models.dtos.RecolteDetail.EmbeddableArbreDetailDTO;
import com.it.citronix.models.dtos.Vente.EmbeddableVenteDTO;
import com.it.citronix.models.enums.Saison;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRecolteDTO {

    private Long id;

    private Saison saison;

    private LocalDate dateRecolte;

    private Double quantiteTotal;

    private Double quantiteRestante;

    private List<EmbeddableArbreDetailDTO> recolteDetails;

    private List<EmbeddableVenteDTO> ventes;

}
