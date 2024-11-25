package com.it.citronix.services.implementations;


import com.it.citronix.models.dtos.Vente.CreateVenteDTO;
import com.it.citronix.models.dtos.Vente.ResponseVenteDTO;
import com.it.citronix.models.dtos.Vente.UpdateVenteDTO;
import com.it.citronix.models.entities.Vente;
import com.it.citronix.models.entities.Recolte;
import com.it.citronix.models.mappers.VenteMapper;
import com.it.citronix.repository.RecolteRepository;
import com.it.citronix.repository.VenteRepository;
import com.it.citronix.services.interfaces.IVenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenteService extends GenericService<Vente, CreateVenteDTO, UpdateVenteDTO, ResponseVenteDTO> implements IVenteService {

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private VenteMapper venteMapper;

    @Autowired
    private RecolteRepository recolteRepository;

    public VenteService(VenteRepository venteRepository, VenteMapper venteMapper) {
        super(venteRepository, venteMapper);
    }

    @Override
    public ResponseVenteDTO create(CreateVenteDTO createVenteDTO) {
        Recolte recolte = recolteRepository.findById(createVenteDTO.getRecolteId()).orElseThrow();
        if (createVenteDTO.getDate().isBefore(recolte.getDateRecolte())) {
            throw new IllegalArgumentException("La date de vente ne peut pas être antérieure à la date de récolte (" + recolte.getDateRecolte() + ").");
        }
        Double quantiteDemande = createVenteDTO.getQuantiteDemande();
        Double quantiteRestante = recolte.getQuantiteRestante();
        if(quantiteDemande > quantiteRestante) {
            throw new IllegalStateException("La quantité demandée (" + quantiteDemande + ") dépasse la quantité restante disponible (" + quantiteRestante + ").");
        }
        Double newQuantiteRestante = quantiteRestante - quantiteDemande;
        recolte.setQuantiteRestante(newQuantiteRestante);
        recolteRepository.save(recolte);

        Vente entity = venteMapper.toEntity(createVenteDTO);
        Vente savedVente = venteRepository.save(entity);
        return  mapper.toDTO(savedVente);
    }

    @Override
    public void deleteById(Long id) {
        Vente vente = venteRepository.findById(id).orElseThrow();
        Recolte recolte = recolteRepository.findById(vente.getId()).orElseThrow();
        Double quantiteDemande = vente.getQuantiteDemande();
        Double quantiteRestante = recolte.getQuantiteRestante();

        Double newQuantiteRestante = quantiteRestante + quantiteDemande;
        recolte.setQuantiteRestante(newQuantiteRestante);
        recolteRepository.save(recolte);
        venteRepository.deleteById(id);
    }

}
