package com.it.citronix.models.embeddableId;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecolteDetailId implements Serializable {
    private Long arbreId;
    private Long recolteId;
}
