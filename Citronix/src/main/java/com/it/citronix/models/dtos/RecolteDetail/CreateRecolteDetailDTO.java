package com.it.citronix.models.dtos.RecolteDetail;


import com.it.citronix.models.embeddableId.RecolteDetailId;
import com.it.citronix.models.enums.Saison;
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
public class CreateRecolteDetailDTO {

    @NotNull
    private EmbeddableIdRecolteDetailDTO id;

}
