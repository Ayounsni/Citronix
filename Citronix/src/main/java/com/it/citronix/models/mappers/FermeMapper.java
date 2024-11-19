package com.it.citronix.models.mappers;

import com.it.citronix.models.dtos.Ferme.CreateFermeDTO;
import com.it.citronix.models.dtos.Ferme.ResponseFermeDTO;
import com.it.citronix.models.dtos.Ferme.UpdateFermeDTO;
import com.it.citronix.models.entities.Ferme;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FermeMapper extends GenericMapper<Ferme, CreateFermeDTO, UpdateFermeDTO, ResponseFermeDTO> {

}
