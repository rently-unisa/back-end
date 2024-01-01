package it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service;

import it.unisa.c02.rently.rently_application.data.dao.GestioneAnnuncioDAO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GestioneAnnuncioServiceImpl implements GestioneAnnuncioService {

    private final GestioneAnnuncioDAO gestioneAnnuncioDAO;

    @Override
    public Optional<Annuncio> getAnnuncio(final long id) {
        return gestioneAnnuncioDAO.findById(id);
    }

    @Override
    public Annuncio updateAnnuncio(Annuncio annuncio) {
        return gestioneAnnuncioDAO.save(annuncio);
    }

    @Override
    public Annuncio addAnnuncio(final Annuncio annuncio) {
        return gestioneAnnuncioDAO.save(annuncio);
    }

    @Override
    public boolean deleteAnnuncio(final Long id) {
        var item = gestioneAnnuncioDAO.findById(id);
        if(item.isEmpty()) {
            return false;
        }

        gestioneAnnuncioDAO.deleteById(id);
        return false;
    }

    @Override
    public List<Annuncio> findAllByUtente(Utente utente) {
        return null;
    }


}
