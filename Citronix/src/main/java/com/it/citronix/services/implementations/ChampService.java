package com.it.citronix.services.implementations;


import com.it.citronix.models.dtos.Champ.CreateChampDTO;
import com.it.citronix.models.dtos.Champ.ResponseChampDTO;
import com.it.citronix.models.dtos.Champ.UpdateChampDTO;
import com.it.citronix.models.entities.Champ;
import com.it.citronix.models.entities.Ferme;
import com.it.citronix.models.mappers.ChampMapper;
import com.it.citronix.repository.ChampRepository;
import com.it.citronix.repository.FermeRepository;
import com.it.citronix.services.interfaces.IChampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampService extends GenericService<Champ, CreateChampDTO, UpdateChampDTO, ResponseChampDTO> implements IChampService {

    @Autowired
    private  ChampRepository champRepository;

    @Autowired
    private FermeRepository fermeRepository;

    @Autowired
    private ChampMapper champMapper;

    public ChampService(ChampRepository champRepository, ChampMapper champMapper) {
        super(champRepository, champMapper);
    }

    @Override
    public ResponseChampDTO create(CreateChampDTO createChampDTO) {
        validateChamp(createChampDTO);
        Champ entity = champMapper.toEntity(createChampDTO);
        Champ savedChamp = champRepository.save(entity);
        return mapper.toDTO(savedChamp);
    }

    private void validateChamp(CreateChampDTO createChampDTO) {
        Ferme ferme = fermeRepository.findById(createChampDTO.getFermeId())
                .orElseThrow();
        Long nombreDeChampsExistants = champRepository.countByFermeId(ferme.getId());
        if (nombreDeChampsExistants >= 10) {
            throw new IllegalStateException("La ferme ne peut pas avoir plus de 10 champs.");
        }
        double superficieTotaleExistante = ferme.getChamps()
                .stream()
                .mapToDouble(Champ::getSuperficie)
                .sum();

        double nouvelleSuperficieTotale = superficieTotaleExistante + createChampDTO.getSuperficie();
        if (nouvelleSuperficieTotale > ferme.getSuperficie()) {
            throw new IllegalStateException("La superficie totale des champs dépasse la superficie disponible de la ferme.");
        }

        double superficieMaximaleParChamp = ferme.getSuperficie() * 0.5;
        if (createChampDTO.getSuperficie() > superficieMaximaleParChamp) {
            throw new IllegalStateException("Un champ ne peut pas dépasser 50% de la superficie totale de la ferme. Le maximum autorisé pour le champ est de " + superficieMaximaleParChamp + " m².");
        }
    }

}
