package it.unisa.c02.rently.rently_application.business.gestioneValutazione.service;


import it.unisa.c02.rently.rently_application.data.dao.GestioneValutazioneOggettoDAO;
import it.unisa.c02.rently.rently_application.data.dao.GestioneValutazioneUtenteDAO;
import it.unisa.c02.rently.rently_application.data.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementazione del servizio di gestione della valutazione.
 * Questa classe fornisce implementazioni concrete per i metodi dichiarati nell'interfaccia GestioneValutazioneService.
 */
@Service
@RequiredArgsConstructor
public class GestioneValutazioneServiceImpl implements GestioneValutazioneService {

    /**
     * L'istanza di GestioneValutazioneUtenteDAO utilizzata per l'accesso ai dati delle valutazioni di utenti.
     */
    private final GestioneValutazioneUtenteDAO valutazioneUtenteDAO;

    /**
     * L'istanza di GestioneValutazioneOggettoDAO utilizzata per l'accesso ai dati delle valutazioni di oggetti.
     */
    private final GestioneValutazioneOggettoDAO valutazioneOggettoDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public ValutazioneUtente addValutazioneUtente (ValutazioneUtente valutazione){
        return valutazioneUtenteDAO.save(valutazione);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteValutazioneUtente(ValutazioneUtente valutazione) {
        valutazioneUtenteDAO.deleteById(valutazione.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValutazioneUtente> findAllByUtente(Utente valutato) {
        return valutazioneUtenteDAO.findByValutato(valutato);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double mediaValutazioniUtenteByUtente(Utente valutato) {
        return valutazioneUtenteDAO.mediaValutazioniUtenteById(valutato.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValutazioneOggetto addValutazioneOggetto(ValutazioneOggetto valutazione) {
        return valutazioneOggettoDAO.save(valutazione);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteValutazioneOggetto(ValutazioneOggetto valutazione) {
        valutazioneOggettoDAO.deleteById(valutazione.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValutazioneOggetto> findAllByAnnuncio(Annuncio annuncio) {
        return valutazioneOggettoDAO.findByAnnuncio(annuncio);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double mediaValutazioniOggettoByAnnuncio(Annuncio annuncio) {
        return valutazioneOggettoDAO.mediaValutazioniOggettoById(annuncio.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean valutazioneNoleggianteIsPresent(Noleggio n) {

        ValutazioneUtente valutazione = valutazioneUtenteDAO.valutazioneNoleggianteIsPresent(n.getId());
        return valutazione != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean valutazioneNoleggiatoreIsPresent(Noleggio n) {

        ValutazioneUtente valutazione = valutazioneUtenteDAO.valutazioneNoleggiatoreIsPresent(n.getId());
        return valutazione != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean valutazioneAnnuncioIsPresent(Noleggio n) {

        ValutazioneOggetto valutazione = valutazioneOggettoDAO.valutazioneAnnuncioIsPresent(n.getId());
        return valutazione != null;
    }
}
