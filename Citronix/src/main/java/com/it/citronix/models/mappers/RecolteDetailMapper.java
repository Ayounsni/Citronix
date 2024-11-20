package com.it.citronix.models.mappers;

import com.it.citronix.models.dtos.RecolteDetail.CreateRecolteDetailDTO;
import com.it.citronix.models.dtos.RecolteDetail.ResponseRecolteDetailDTO;
import com.it.citronix.models.dtos.RecolteDetail.UpdateRecolteDetailDTO;
import com.it.citronix.models.entities.RecolteDetail;
import com.it.citronix.models.mappers.helpers.ArbreMapperHelper;
import com.it.citronix.models.mappers.helpers.RecolteMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",uses = {RecolteMapperHelper.class , ArbreMapperHelper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RecolteDetailMapper extends GenericMapper<RecolteDetail, CreateRecolteDetailDTO, UpdateRecolteDetailDTO, ResponseRecolteDetailDTO> {
    @Override
    @Mapping(target = "arbre", source = "id.arbreId")
    @Mapping(target = "recolte", source = "id.recolteId")
    RecolteDetail toEntity(CreateRecolteDetailDTO createDTO);

    @Override
    @Mapping(target = "arbre", source = "id.arbreId")
    @Mapping(target = "recolte", source = "id.recolteId")
    RecolteDetail updateEntityFromDTO(UpdateRecolteDetailDTO updateRecolteDetailDTO, @MappingTarget RecolteDetail entity);

}
