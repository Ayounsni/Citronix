package com.it.citronix.services.implementations;


import com.it.citronix.models.dtos.Arbre.CreateArbreDTO;
import com.it.citronix.models.dtos.Arbre.ResponseArbreDTO;
import com.it.citronix.models.dtos.Arbre.UpdateArbreDTO;
import com.it.citronix.models.dtos.Recolte.CreateRecolteDTO;
import com.it.citronix.models.dtos.Recolte.ResponseRecolteDTO;
import com.it.citronix.models.dtos.Recolte.UpdateRecolteDTO;
import com.it.citronix.models.entities.Arbre;
import com.it.citronix.models.entities.Recolte;
import com.it.citronix.models.enums.Saison;
import com.it.citronix.models.mappers.RecolteMapper;
import com.it.citronix.repository.RecolteRepository;
import com.it.citronix.services.interfaces.IRecolteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RecolteService extends GenericService<Recolte, CreateRecolteDTO, UpdateRecolteDTO, ResponseRecolteDTO> implements IRecolteService {

    @Autowired
    private RecolteRepository recolteRepository;

    @Autowired
    private RecolteMapper recolteMapper;

    public RecolteService(RecolteRepository recolteRepository, RecolteMapper recolteMapper) {
        super(recolteRepository, recolteMapper);
    }

    @Override
    public ResponseRecolteDTO create(CreateRecolteDTO createRecolteDTO){

        Saison saison = determineSaison(createRecolteDTO.getDateRecolte());

        Recolte entity = recolteMapper.toEntity(createRecolteDTO);
        entity.setSaison(saison);
        Recolte savedRecolte = recolteRepository.save(entity);
        return  mapper.toDTO(savedRecolte);
    }

    @Override
    public ResponseRecolteDTO update(Long id, UpdateRecolteDTO updateRecolteDTO) {
        Recolte existingRecolte = recolteRepository.findById(id)
                .orElseThrow();

        if (existingRecolte.getRecolteDetails() != null && !existingRecolte.getRecolteDetails().isEmpty()) {
            throw new IllegalStateException("Vous ne pouvez pas modifier cette récolte car elle est déjà commencée.");
        }

        Saison saison = determineSaison(updateRecolteDTO.getDateRecolte());

        recolteMapper.updateEntityFromDTO(updateRecolteDTO, existingRecolte);
        existingRecolte.setSaison(saison);
        Recolte updatedRecolte = recolteRepository.save(existingRecolte);

        return mapper.toDTO(updatedRecolte);
    }

    private Saison determineSaison(LocalDate date) {
        int month = date.getMonthValue();

        if (month == 12 || month == 1 || month == 2) {
            return Saison.HIVER;
        } else if (month >= 3 && month <= 5) {
            return Saison.PRINTEMPS;
        } else if (month >= 6 && month <= 8) {
            return Saison.ETE;
        } else if (month >= 9 && month <= 11) {
            return Saison.AUTOMNE;
        }
        throw new IllegalArgumentException("Date invalide pour déterminer la saison.");
    }

}
