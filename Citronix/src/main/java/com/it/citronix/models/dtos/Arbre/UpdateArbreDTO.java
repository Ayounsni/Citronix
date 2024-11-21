package com.it.citronix.models.dtos.Arbre;


import com.it.citronix.models.entities.Champ;
import com.it.citronix.validation.annotations.Exists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArbreDTO {

    @Exists(entity = Champ.class, message = "Ce champ n'existe pas.")
    private Long champId;

    private LocalDate datePlantation;

}
