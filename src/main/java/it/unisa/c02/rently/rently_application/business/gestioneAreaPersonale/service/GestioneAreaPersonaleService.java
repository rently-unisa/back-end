package it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service;

import it.unisa.c02.rently.rently_application.data.model.Utente;

/**
 * Questa interfaccia definisce le specifiche per i servizi dedicati alla gestione dell'area personale.
 */
public interface GestioneAreaPersonaleService {

    /**
     * Aggiorna le informazioni dell'utente.
     *
     * @param utente Utente contenente le nuove informazioni dell'utente.
     * @return Utente aggiornato con le nuove informazioni.
     */
    Utente updateUtente(Utente utente);

    /**
     * Restituisce i dati privati dell'utente identificato dal suo ID.
     *
     * @param id ID dell'utente di cui si vogliono ottenere i dati privati.
     * @return Utente contenente i dati privati dell'utente.
     */
    Utente getDatiPrivati(long id);

    /**
     * Restituisce un utente nel sistema in base al suo username.
     *
     * @param utente Utente contenente l'username dell'utente da cercare.
     * @return Utente corrispondente all'username specificato.
     */
    Utente findByUsername(Utente utente);
}
