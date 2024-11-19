package com.it.itlens.models.dtos.Owner;

import com.it.itlens.models.entities.Owner;
import com.it.itlens.validation.annotations.Unique;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOwnerDTO {
    @NotBlank
    @Unique(entity = Owner.class, field = "name")
    private String name;
}
