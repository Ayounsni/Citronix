package com.it.citronix.models.mappers;

import com.it.citronix.models.dtos.Champ.CreateChampDTO;
import com.it.citronix.models.dtos.Champ.ResponseChampDTO;
import com.it.citronix.models.dtos.Champ.UpdateChampDTO;
import com.it.citronix.models.entities.Champ;
import com.it.citronix.models.mappers.helpers.FermeMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",uses = {FermeMapperHelper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChampMapper extends GenericMapper<Champ, CreateChampDTO, UpdateChampDTO, ResponseChampDTO> {
    @Override
    @Mapping(target = "ferme", source = "fermeId")
    Champ toEntity(CreateChampDTO createDTO);

    @Override
    @Mapping(target = "ferme", source = "fermeId")
    Champ updateEntityFromDTO(UpdateChampDTO updateChampDTO, @MappingTarget Champ entity);



}
