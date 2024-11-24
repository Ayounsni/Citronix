package com.it.citronix.models.dtos.Champ;


import com.it.citronix.models.dtos.Arbre.EmbeddableArbreDTO;
import com.it.citronix.models.dtos.Ferme.EmbeddableFermeDTO;
import com.it.citronix.models.entities.Ferme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChampDTO {

    private Long id;

    private EmbeddableFermeDTO ferme;

    private Double superficie;

    private List<EmbeddableArbreDTO> arbres;

}
