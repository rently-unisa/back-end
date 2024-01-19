package it.unisa.c02.rently.rently_application.business.gestioneValutazione.service;

import it.unisa.c02.rently.rently_application.data.model.*;

import java.util.List;

public interface GestioneValutazioneService {

    ValutazioneUtente addValutazioneUtente (ValutazioneUtente valutazione);
    void deleteValutazioneUtente (ValutazioneUtente valutazione);
    List<ValutazioneUtente> findAllByUtente(Utente valutato);
    double mediaValutazioniUtenteByUtente(Utente valutato);
    ValutazioneOggetto addValutazioneOggetto (ValutazioneOggetto valutazione);
    void deleteValutazioneOggetto(ValutazioneOggetto valutazione);
    List<ValutazioneOggetto> findAllByAnnuncio(Annuncio annuncio);
    double mediaValutazioniOggettoByAnnuncio(Annuncio annuncio);
    boolean valutazioneNoleggianteIsPresent(Noleggio n);
    boolean valutazioneNoleggiatoreIsPresent(Noleggio n);
    boolean valutazioneAnnuncioIsPresent(Noleggio n);
}
