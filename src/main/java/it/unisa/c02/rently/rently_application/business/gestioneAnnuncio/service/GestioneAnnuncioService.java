package it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Utente;

import java.util.List;
import java.util.Optional;

/**
 * Questa interfaccia definisce le specifiche per i servizi dedicati alla gestione degli annunci.
 */
public interface GestioneAnnuncioService {

    /**
     * Restituisce un annuncio specifico in base all'identificativo.
     *
     * @param id Identificativo dell'annuncio.
     * @return Optional contenente l'annuncio se presente, altrimenti Optional vuoto.
     */
    Optional<Annuncio> getAnnuncio(long id);

    /**
     * Aggiorna le informazioni di un annuncio sulla piattaforma.
     *
     * @param annuncio Annuncio da aggiornare.
     * @return Annuncio aggiornato.
     */
    Annuncio updateAnnuncio(Annuncio annuncio);

    /**
     * Aggiunge un nuovo annuncio alla piattaforma.
     *
     * @param annuncio Annuncio da aggiungere.
     * @return Annuncio aggiunto.
     */
    Annuncio addAnnuncio(Annuncio annuncio);

    /**
     * Elimina un annuncio dalla piattaforma in base all'identificativo.
     *
     * @param id Identificativo dell'annuncio da eliminare.
     * @return true se l'annuncio è stato eliminato con successo, altrimenti false.
     */
    boolean deleteAnnuncio(Long id);

    /**
     * Restituisce tutti gli annunci associati a un utente specifico.
     *
     * @param utente Utente associato agli annunci.
     * @return Lista di annunci associati all'utente specificato.
     */
    List<Annuncio> findAllByUtente(Utente utente);
}
