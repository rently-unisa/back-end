package it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service;

import it.unisa.c02.rently.rently_application.data.dao.GestioneAnnuncioDAO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementazione del servizio di gestione degli annunci.
 * Questa classe fornisce implementazioni concrete per i metodi dichiarati nell'interfaccia GestioneAnnuncioService.
 */
@Service
@RequiredArgsConstructor
public class GestioneAnnuncioServiceImpl implements GestioneAnnuncioService {

    /**
     * Istanza di GestioneAnnuncioDAO utilizzata per l'accesso ai dati degli annunci.
     */
    private final GestioneAnnuncioDAO gestioneAnnuncioDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Annuncio> getAnnuncio(final long id) {
        return gestioneAnnuncioDAO.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Annuncio updateAnnuncio(Annuncio annuncio) {
        return gestioneAnnuncioDAO.save(annuncio);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Annuncio addAnnuncio(final Annuncio annuncio) {
        return gestioneAnnuncioDAO.save(annuncio);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAnnuncio(final Long id) {
        var item = gestioneAnnuncioDAO.findById(id);
        if(item.isEmpty()) {
            return false;
        }

        gestioneAnnuncioDAO.deleteById(id);
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Annuncio> findAllByUtente(Utente utente) {
        return gestioneAnnuncioDAO.findByUtente(utente);
    }
}
