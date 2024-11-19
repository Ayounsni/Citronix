package com.it.citronix.models.entities;

import com.it.citronix.models.embeddableId.RecolteDetailId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecolteDetail {

    @EmbeddedId
    private RecolteDetailId id;

    @ManyToOne
    @MapsId("arbreId")
    private Arbre arbre;

    @ManyToOne
    @MapsId("recolteId")
    private Recolte recolte;

    @NotNull
    private Double quantiteRecolte;
}
