package com.it.citronix.models.dtos.Arbre;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArbreDTO {

    private Long champId;

    private LocalDate datePlantation;

}
