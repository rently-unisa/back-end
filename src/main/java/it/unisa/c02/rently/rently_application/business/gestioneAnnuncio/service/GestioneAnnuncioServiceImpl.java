package it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service;

import it.unisa.c02.rently.rently_application.data.dao.GestioneAnnuncioDAO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GestioneAnnuncioServiceImpl implements GestioneAnnuncioService {

    private final GestioneAnnuncioDAO gestioneAnnuncioDAO;
    @Override
    public Annuncio addAnnuncio(final Annuncio annuncio) {
        return gestioneAnnuncioDAO.save(annuncio);
    }


}
