package com.it.citronix.services.implementations;


import com.it.citronix.models.dtos.Vente.CreateVenteDTO;
import com.it.citronix.models.dtos.Vente.ResponseVenteDTO;
import com.it.citronix.models.dtos.Vente.UpdateVenteDTO;
import com.it.citronix.models.entities.Vente;
import com.it.citronix.models.mappers.VenteMapper;
import com.it.citronix.repository.VenteRepository;
import com.it.citronix.services.interfaces.IVenteService;
import org.springframework.stereotype.Service;

@Service
public class VenteService extends GenericService<Vente, CreateVenteDTO, UpdateVenteDTO, ResponseVenteDTO> implements IVenteService {

    public VenteService(VenteRepository venteRepository, VenteMapper venteMapper) {
        super(venteRepository, venteMapper);
    }

}
