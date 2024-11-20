package com.it.citronix.models.mappers;

import com.it.citronix.models.dtos.Recolte.CreateRecolteDTO;
import com.it.citronix.models.dtos.Recolte.ResponseRecolteDTO;
import com.it.citronix.models.dtos.Recolte.UpdateRecolteDTO;
import com.it.citronix.models.entities.Recolte;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RecolteMapper extends GenericMapper<Recolte, CreateRecolteDTO, UpdateRecolteDTO, ResponseRecolteDTO> {

}
