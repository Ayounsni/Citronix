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
import org.mapstruct.*;

@Mapper(componentModel = "spring",uses = {ChampMapperHelper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArbreMapper extends GenericMapper<Arbre, CreateArbreDTO, UpdateArbreDTO, ResponseArbreDTO> {

    @Override
    @Mapping(target = "champ", source = "champId")
    Arbre toEntity(CreateArbreDTO createDTO);

    @Override
    @Mapping(target = "champ", source = "champId")
    Arbre updateEntityFromDTO(UpdateArbreDTO updateArbreDTO, @MappingTarget Arbre entity);

    @AfterMapping
    default void enrichirDTO(@MappingTarget ResponseArbreDTO response, Arbre arbre) {
        String age = arbre.calculerAge();
        response.setAge(age);

        double productiviteAnnuelle = arbre.calculerProductiviteAnnuel();
        response.setEsmtimationProductiviteAnnuel(productiviteAnnuelle);
    }

}
