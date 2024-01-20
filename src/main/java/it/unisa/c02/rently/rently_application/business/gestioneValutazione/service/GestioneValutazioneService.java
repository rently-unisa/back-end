package it.unisa.c02.rently.rently_application.business.gestioneValutazione.service;

import it.unisa.c02.rently.rently_application.data.model.*;

import java.util.List;

/**
 * Questa interfaccia definisce le specifiche per i servizi dedicati alla gestione della valutazione.
 */
public interface GestioneValutazioneService {

    /**
     * Aggiunge una valutazione dell'utente sulla piattaforma.
     *
     * @param valutazione Valutazione utente da aggiungere.
     * @return Valutazione utente aggiunta.
     */
    ValutazioneUtente addValutazioneUtente (ValutazioneUtente valutazione);

    /**
     * Elimina una valutazione dell'utente dalla piattaforma.
     *
     * @param valutazione Valutazione utente da eliminare.
     */
    void deleteValutazioneUtente (ValutazioneUtente valutazione);

    /**
     * Restituisce tutte le valutazioni associate a un utente specifico.
     *
     * @param valutato Utente specifico.
     * @return Lista di valutazioni utente associate all'utente specificato.
     */
    List<ValutazioneUtente> findAllByUtente(Utente valutato);

    /**
     * Calcola la media delle valutazioni associate a un utente specifico.
     *
     * @param valutato Utente specifico.
     * @return Media delle valutazioni dell'utente specificato.
     */
    double mediaValutazioniUtenteByUtente(Utente valutato);

    /**
     * Aggiunge una valutazione dell'oggetto sulla piattaforma.
     *
     * @param valutazione Valutazione oggetto da aggiungere.
     * @return Valutazione oggetto aggiunta.
     */
    ValutazioneOggetto addValutazioneOggetto (ValutazioneOggetto valutazione);

    /**
     * Elimina una valutazione dell'oggetto dalla piattaforma.
     *
     * @param valutazione Valutazione oggetto da eliminare.
     */
    void deleteValutazioneOggetto(ValutazioneOggetto valutazione);

    /**
     * Restituisce tutte le valutazioni associate a un annuncio specifico.
     *
     * @param annuncio Annuncio specifico.
     * @return Lista di valutazioni oggetto associate all'annuncio specificato.
     */
    List<ValutazioneOggetto> findAllByAnnuncio(Annuncio annuncio);

    /**
     * Calcola la media delle valutazioni associate a un annuncio specifico.
     *
     * @param annuncio Annuncio specifico.
     * @return Media delle valutazioni dell'annuncio specificato.
     */
    double mediaValutazioniOggettoByAnnuncio(Annuncio annuncio);

    /**
     * Verifica se esiste una valutazione associata al noleggiante in un noleggio specifico.
     *
     * @param n Noleggio specifico.
     * @return true se esiste una valutazione associata al noleggiante, altrimenti false.
     */
    boolean valutazioneNoleggianteIsPresent(Noleggio n);

    /**
     * Verifica se esiste una valutazione associata al noleggiatore in un noleggio specifico.
     *
     * @param n Noleggio specifico.
     * @return true se esiste una valutazione associata al noleggiatore, altrimenti false.
     */
    boolean valutazioneNoleggiatoreIsPresent(Noleggio n);

    /**
     * Verifica se esiste una valutazione associata all'annuncio in un noleggio specifico.
     *
     * @param n Noleggio specifico.
     * @return true se esiste una valutazione associata all'annuncio, altrimenti false.
     */
    boolean valutazioneAnnuncioIsPresent(Noleggio n);
}
