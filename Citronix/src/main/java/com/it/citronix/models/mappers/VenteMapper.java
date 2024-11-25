package com.it.citronix.models.mappers;

import com.it.citronix.models.dtos.Arbre.ResponseArbreDTO;
import com.it.citronix.models.dtos.Vente.CreateVenteDTO;
import com.it.citronix.models.dtos.Vente.UpdateVenteDTO;
import com.it.citronix.models.dtos.Vente.ResponseVenteDTO;
import com.it.citronix.models.entities.Arbre;
import com.it.citronix.models.entities.Vente;
import com.it.citronix.models.mappers.helpers.RecolteMapperHelper;
import org.mapstruct.*;

@Mapper(componentModel = "spring",uses = {RecolteMapperHelper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VenteMapper extends GenericMapper<Vente, CreateVenteDTO, UpdateVenteDTO, ResponseVenteDTO> {
    @Override
    @Mapping(target = "recolte", source = "recolteId")
    Vente toEntity(CreateVenteDTO createDTO);

    @Override
    @Mapping(target = "recolte", source = "recolteId")
    Vente updateEntityFromDTO(UpdateVenteDTO updateVenteDTO, @MappingTarget Vente entity);

    @AfterMapping
    default void enrichirDTO(@MappingTarget ResponseVenteDTO response, Vente vente) {
        Double revenue = vente.getQuantiteDemande() * vente.getPrixUnitaire();
        response.setRevenue(revenue);
    }

}
