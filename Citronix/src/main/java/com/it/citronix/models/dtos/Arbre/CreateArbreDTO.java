package com.it.citronix.models.dtos.Arbre;


import com.it.citronix.models.entities.Champ;
import com.it.citronix.models.entities.Ferme;
import com.it.citronix.validation.annotations.Exists;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateArbreDTO {

    @NotNull
    @Exists(entity = Champ.class, message = "Ce champ n'existe pas.")
    private Long champId;

    @NotNull
    @PastOrPresent(message = "La date de plantation ne peut pas être dans le futur.")
    private LocalDate datePlantation;

}
