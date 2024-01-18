package it.unisa.c02.rently.rently_application.business.gestioneValutazione.service;


import it.unisa.c02.rently.rently_application.data.dao.GestioneValutazioneOggettoDAO;
import it.unisa.c02.rently.rently_application.data.dao.GestioneValutazioneUtenteDAO;
import it.unisa.c02.rently.rently_application.data.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GestioneValutazioneServiceImpl implements GestioneValutazioneService {

    private final GestioneValutazioneUtenteDAO valutazioneUtenteDAO;
    private final GestioneValutazioneOggettoDAO valutazioneOggettoDAO;
    @Override
    public ValutazioneUtente addValutazioneUtente (ValutazioneUtente valutazione){
        return valutazioneUtenteDAO.save(valutazione);
    }

    @Override
    public void deleteValutazioneUtente(ValutazioneUtente valutazione) {
        valutazioneUtenteDAO.deleteById(valutazione.getId());
    }

    @Override
    public List<ValutazioneUtente> findAllByUtente(Utente valutato) {
        return valutazioneUtenteDAO.findByValutato(valutato);
    }

    @Override
    public double mediaValutazioniUtenteByUtente(Utente valutato) {
        return valutazioneUtenteDAO.mediaValutazioniUtenteById(valutato.getId());
    }

    @Override
    public ValutazioneOggetto addValutazioneOggetto(ValutazioneOggetto valutazione) {
        return valutazioneOggettoDAO.save(valutazione);
    }

    @Override
    public void deleteValutazioneOggetto(ValutazioneOggetto valutazione) {
        valutazioneOggettoDAO.deleteById(valutazione.getId());
    }

    @Override
    public List<ValutazioneOggetto> findAllByAnnuncio(Annuncio annuncio) {
        return valutazioneOggettoDAO.findByAnnuncio(annuncio);
    }

    @Override
    public double mediaValutazioniOggettoByAnnuncio(Annuncio annuncio) {
        return valutazioneOggettoDAO.mediaValutazioniOggettoById(annuncio.getId());
    }

    @Override
    public boolean valutazioneNoleggianteIsPresent(Noleggio n) {

        ValutazioneUtente valutazione = valutazioneUtenteDAO.valutazioneNoleggianteIsPresent(n.getId());
        return valutazione != null;
    }

    @Override
    public boolean valutazioneNoleggiatoreIsPresent(Noleggio n) {

        ValutazioneUtente valutazione = valutazioneUtenteDAO.valutazioneNoleggiatoreIsPresent(n.getId());
        return valutazione != null;
    }

    @Override
    public boolean valutazioneAnnuncioIsPresent(Noleggio n) {

        ValutazioneOggetto valutazione = valutazioneOggettoDAO.valutazioneAnnuncioIsPresent(n.getId());
        return valutazione != null;
    }


}
