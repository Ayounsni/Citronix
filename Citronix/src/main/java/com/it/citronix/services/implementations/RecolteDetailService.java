package com.it.citronix.services.implementations;

import com.it.citronix.models.dtos.RecolteDetail.CreateRecolteDetailDTO;
import com.it.citronix.models.dtos.RecolteDetail.ResponseRecolteDetailDTO;
import com.it.citronix.models.entities.Arbre;
import com.it.citronix.models.entities.Recolte;
import com.it.citronix.models.entities.RecolteDetail;
import com.it.citronix.models.enums.Saison;
import com.it.citronix.models.mappers.RecolteDetailMapper;
import com.it.citronix.repository.ArbreRepository;
import com.it.citronix.repository.RecolteDetailRepository;
import com.it.citronix.repository.RecolteRepository;
import com.it.citronix.services.interfaces.IRecolteDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecolteDetailService implements IRecolteDetailService {


    private final RecolteDetailRepository recolteDetailRepository;
    private final RecolteDetailMapper recolteDetailMapper;
    private final RecolteRepository recolteRepository;
    private final ArbreRepository arbreRepository;

    public ResponseRecolteDetailDTO create(CreateRecolteDetailDTO createRecolteDetailDTO) {
//        Arbre arbre = arbreRepository.findById(createRecolteDetailDTO.getId().getArbreId())
//                .orElseThrow(() -> new IllegalArgumentException("L'arbre spécifié n'existe pas."));

        Recolte recolte = recolteRepository.findById(createRecolteDetailDTO.getId().getRecolteId())
               .orElseThrow(() -> new IllegalArgumentException("Le récolte spécifié n'existe pas."));
       Saison saison = recolte.getSaison();
       int annee = recolte.getDateRecolte().getYear();
       Long arbreId = createRecolteDetailDTO.getId().getArbreId();
       validateRecolteDetailForArbre(arbreId, saison, annee);

        RecolteDetail recolteDetail = recolteDetailMapper.toEntity(createRecolteDetailDTO);
        RecolteDetail savedRecolteDetail = recolteDetailRepository.save(recolteDetail);
        return recolteDetailMapper.toDTO(savedRecolteDetail);

    }



    public void validateRecolteDetailForArbre(Long arbreId, Saison saison, int annee) {
        List<RecolteDetail> recolteDetails = recolteDetailRepository.findByArbreId(arbreId);

        // Filtrer et comparer avec la saison et l'année
        boolean exists = recolteDetails.stream()
                .anyMatch(detail ->
                        detail.getRecolte().getSaison() == saison &&
                                detail.getRecolte().getDateRecolte().getYear() == annee
                );

        if (exists) {
            throw new IllegalArgumentException("L'arbre a déjà été récolté pour cette saison et cette année.");
        }
    }




}
