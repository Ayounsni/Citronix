package com.it.citronix.services.implementations;


import com.it.citronix.models.dtos.Arbre.CreateArbreDTO;
import com.it.citronix.models.dtos.Arbre.ResponseArbreDTO;
import com.it.citronix.models.dtos.Arbre.UpdateArbreDTO;
import com.it.citronix.models.entities.Arbre;
import com.it.citronix.models.entities.Champ;
import com.it.citronix.models.mappers.ArbreMapper;
import com.it.citronix.repository.ArbreRepository;
import com.it.citronix.repository.ChampRepository;
import com.it.citronix.services.interfaces.IArbreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ArbreService extends GenericService<Arbre, CreateArbreDTO, UpdateArbreDTO, ResponseArbreDTO> implements IArbreService {

    @Autowired
    ArbreRepository arbreRepository;

    @Autowired
    ArbreMapper arbreMapper;

    @Autowired
    ChampRepository champRepository;

    public ArbreService(ArbreRepository arbreRepository, ArbreMapper arbreMapper) {
        super(arbreRepository, arbreMapper);
    }

    @Override
    public ResponseArbreDTO create(CreateArbreDTO createArbreDTO){

        validateArbre(createArbreDTO.getChampId(), createArbreDTO.getDatePlantation());

        Arbre entity = arbreMapper.toEntity(createArbreDTO);
        Arbre savedArbre = arbreRepository.save(entity);
        return  mapper.toDTO(savedArbre);
    }

    @Override
    public ResponseArbreDTO update(Long id, UpdateArbreDTO updateArbreDTO) {
        Arbre existingArbre = arbreRepository.findById(id)
                .orElseThrow();

        if(existingArbre.getChamp().getId().equals(updateArbreDTO.getChampId())){
            Champ champ = champRepository.findById(updateArbreDTO.getChampId())
                    .orElseThrow(() -> new IllegalArgumentException("Le champ spécifié n'existe pas."));
            LocalDate dateCreationFerme = champ.getFerme().getDateCreation();
            if (updateArbreDTO.getDatePlantation().isBefore(dateCreationFerme)) {
                throw new IllegalArgumentException("La date de plantation ne peut pas être antérieure à la date de création de la ferme (" + dateCreationFerme + ").");
            }

            int month = updateArbreDTO.getDatePlantation().getMonthValue();
            if (month < 3 || month > 5) {
                throw new IllegalArgumentException("La période de plantation doit être comprise entre mars et mai.");
            }
        }else{
            validateArbre(updateArbreDTO.getChampId(), updateArbreDTO.getDatePlantation());
        }

        arbreMapper.updateEntityFromDTO(updateArbreDTO, existingArbre);
        Arbre updatedArbre = arbreRepository.save(existingArbre);

        return mapper.toDTO(updatedArbre);
    }


    private void validateArbre(Long champId, LocalDate datePlantation) {
        Champ champ = champRepository.findById(champId)
                .orElseThrow(() -> new IllegalArgumentException("Le champ spécifié n'existe pas."));
        LocalDate dateCreationFerme = champ.getFerme().getDateCreation();
        if (datePlantation.isBefore(dateCreationFerme)) {
            throw new IllegalArgumentException("La date de plantation ne peut pas être antérieure à la date de création de la ferme (" + dateCreationFerme + ").");
        }

        int month = datePlantation.getMonthValue();
        if (month < 3 || month > 5) {
            throw new IllegalArgumentException("La période de plantation doit être comprise entre mars et mai.");
        }

        Long nombreArbresExistants = arbreRepository.countByChampId(champ.getId());
        double superficieChamp = champ.getSuperficie();
        double densiteActuelle = (nombreArbresExistants + 1) / superficieChamp;

        double densiteMaximale = 10.0 / 1000.0;
        double nombreArbreMax = superficieChamp * densiteMaximale;
        if (densiteActuelle > densiteMaximale) {
            throw new IllegalStateException("La densité maximale d'arbres pour ce champ est dépassée. Le maximum autorisé pour ce champ est de " + nombreArbreMax + " arbres.");
        }
    }

}
