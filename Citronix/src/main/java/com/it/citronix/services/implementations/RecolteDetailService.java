package com.it.citronix.services.implementations;

import com.it.citronix.models.dtos.Pagination.PageDTO;
import com.it.citronix.models.dtos.RecolteDetail.CreateRecolteDetailDTO;
import com.it.citronix.models.dtos.RecolteDetail.ResponseRecolteDetailDTO;
import com.it.citronix.models.embeddableId.RecolteDetailId;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public ResponseRecolteDetailDTO create(CreateRecolteDetailDTO createRecolteDetailDTO) {
        Arbre arbre = arbreRepository.findById(createRecolteDetailDTO.getId().getArbreId())
                .orElseThrow(() -> new IllegalArgumentException("L'arbre spécifié n'existe pas."));

        Recolte recolte = recolteRepository.findById(createRecolteDetailDTO.getId().getRecolteId())
               .orElseThrow(() -> new IllegalArgumentException("Le récolte spécifié n'existe pas."));

        if (recolte.getDateRecolte().isBefore(arbre.getDatePlantation())) {
            throw new IllegalArgumentException("La date de recolte ne peut pas être antérieure à la date de plantation (" + arbre.getDatePlantation() + ").");
        }
       Saison saison = recolte.getSaison();
       int annee = recolte.getDateRecolte().getYear();
       Long arbreId = createRecolteDetailDTO.getId().getArbreId();
       Long champId = arbre.getChamp().getId();

        validateRecolteDetailForArbre(arbreId, saison, annee);
        validateRecolteDetailForChamp(champId, saison, annee, recolte.getId());

        RecolteDetail recolteDetail = recolteDetailMapper.toEntity(createRecolteDetailDTO);
        RecolteDetail savedRecolteDetail = recolteDetailRepository.save(recolteDetail);
        Double quantiteToltal = recolte.getQuantiteTotal() + createRecolteDetailDTO.getQuantiteRecolte();
        Double quantiteRestante = recolte.getQuantiteRestante() + createRecolteDetailDTO.getQuantiteRecolte();
        recolte.setQuantiteTotal(quantiteToltal);
        recolte.setQuantiteRestante(quantiteRestante);
        recolteRepository.save(recolte);

        return recolteDetailMapper.toDTO(savedRecolteDetail);

    }
    @Override
    public void deleteRecolteDetailById(Long recolteId, Long arbreId) {
        Recolte recolte = recolteRepository.findById(recolteId)
                .orElseThrow(() -> new IllegalArgumentException("Récolte avec ID " + recolteId + " n'existe pas."));

        RecolteDetailId recolteDetailId = new RecolteDetailId(recolteId, arbreId);

        RecolteDetail recolteDetail = recolteDetailRepository.findById(recolteDetailId)
                .orElseThrow(() -> new IllegalArgumentException("RécolteDetail avec récolte ID " + recolteId + " et arbre ID " + arbreId + " n'existe pas."));

        if (recolte.getVentes() != null && !recolte.getVentes().isEmpty()) {
            throw new IllegalStateException("Vous ne pouvez pas supprimer cette RécolteDetail car la vente est déjà commencée.");
        }

        double quantiteTotal = recolte.getQuantiteTotal() - recolteDetail.getQuantiteRecolte();
        double quantiteRestante = recolte.getQuantiteRestante() - recolteDetail.getQuantiteRecolte();
        recolte.setQuantiteTotal(quantiteTotal);
        recolte.setQuantiteRestante(quantiteRestante);

        recolteDetailRepository.deleteById(recolteDetailId);
        recolteRepository.save(recolte);
    }

    @Override
    public PageDTO<ResponseRecolteDetailDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RecolteDetail> pagedResult = recolteDetailRepository.findAll(pageable);

        List<ResponseRecolteDetailDTO> content = pagedResult.getContent()
                .stream()
                .map(recolteDetailMapper::toDTO)
                .toList();

        return new PageDTO<>(
                content,
                pagedResult.getNumber(),
                pagedResult.getSize(),
                pagedResult.getTotalElements(),
                pagedResult.getTotalPages(),
                pagedResult.isLast()
        );
    }
    @Override
    public ResponseRecolteDetailDTO getRecolteDetailById(Long RecolteId, Long ArbreId) {
        RecolteDetailId recolteDetailId = new RecolteDetailId(RecolteId,ArbreId);
        RecolteDetail recolteDetail = recolteDetailRepository.findById(recolteDetailId)
                .orElseThrow(() -> new IllegalArgumentException("RecolteDetail avec récolte ID " + RecolteId + " et arbre ID " + ArbreId + " n'existe pas."));

        return recolteDetailMapper.toDTO(recolteDetail);
    }





    public void validateRecolteDetailForArbre(Long arbreId, Saison saison, int annee) {
        List<RecolteDetail> recolteDetails = recolteDetailRepository.findByArbreId(arbreId);
        boolean exists = recolteDetails.stream()
                .anyMatch(detail ->
                        detail.getRecolte().getSaison() == saison &&
                                detail.getRecolte().getDateRecolte().getYear() == annee
                );
        if (exists) {
            throw new IllegalArgumentException("L'arbre a déjà été récolté pour cette saison et cette année.");
        }
    }

    public void validateRecolteDetailForChamp(Long champId, Saison saison, int annee, Long recolteId) {
        List<Arbre> arbresDuChamp = arbreRepository.findByChampId(champId);

        List<Long> arbreIds = arbresDuChamp.stream()
                .map(Arbre::getId)
                .toList();

        boolean exists = recolteDetailRepository.findAll().stream()
                .anyMatch(detail ->
                        arbreIds.contains(detail.getArbre().getId()) &&
                                detail.getRecolte().getSaison() == saison &&
                                detail.getRecolte().getDateRecolte().getYear() == annee &&
                                !detail.getRecolte().getId().equals(recolteId)
                );

        if (exists) {
            throw new IllegalArgumentException("Le champ contient déjà une récolte pour cette saison et cette année.");
        }
    }





}
