package it.unisa.c02.rently.rently_application.business.gestioneRicerca.service;

import it.unisa.c02.rently.rently_application.data.dao.GestioneAnnuncioDAO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Implementazione del servizio di gestione della ricerca di annunci.
 * Questa classe fornisce implementazioni concrete per i metodi dichiarati nell'interfaccia GestioneRicercaService.
 */
@Service
@RequiredArgsConstructor
public class GestioneRicercaServiceImpl implements GestioneRicercaService {

    /**
     * Istanza di GestioneAnnuncioDAO utilizzata per l'accesso ai dati degli annunci.
     */
    private final GestioneAnnuncioDAO annuncioDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Annuncio> searchByCategoria(Annuncio.EnumCategoria categoria) {
        return annuncioDAO.findByCategoria(categoria);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Annuncio> searchByCondizione(String condizione) {
        return annuncioDAO.findByCondizione(condizione);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Annuncio> searchByData(Date inizio, Date fine) {
        return annuncioDAO.findByDataFineBetween(inizio, fine);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Annuncio> searchByDescrizione(String descrizione) {
        return annuncioDAO.findByDescrizioneContains(descrizione);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Annuncio> searchAll() {
        return annuncioDAO.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Annuncio> searchAnnunciPremium() {
        return annuncioDAO.findAllPremium();
    }
}

