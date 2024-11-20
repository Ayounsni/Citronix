package com.it.citronix.models.mappers;

import com.it.citronix.models.dtos.Arbre.CreateArbreDTO;
import com.it.citronix.models.dtos.Arbre.ResponseArbreDTO;
import com.it.citronix.models.dtos.Arbre.UpdateArbreDTO;
import com.it.citronix.models.dtos.Arbre.CreateArbreDTO;
import com.it.citronix.models.dtos.Arbre.UpdateArbreDTO;
import com.it.citronix.models.entities.Arbre;
import com.it.citronix.models.entities.Arbre;
import com.it.citronix.models.mappers.helpers.ChampMapperHelper;
import com.it.citronix.models.mappers.helpers.FermeMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",uses = {ChampMapperHelper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArbreMapper extends GenericMapper<Arbre, CreateArbreDTO, UpdateArbreDTO, ResponseArbreDTO> {

    @Override
    @Mapping(target = "champ", source = "champId")
    Arbre toEntity(CreateArbreDTO createDTO);

    @Override
    @Mapping(target = "champ", source = "champId")
    Arbre updateEntityFromDTO(UpdateArbreDTO updateArbreDTO, @MappingTarget Arbre entity);

}
