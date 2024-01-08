package it.unisa.c02.rently.rently_application.business.gestioneValutazione.service;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneOggetto;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneUtente;

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
}
