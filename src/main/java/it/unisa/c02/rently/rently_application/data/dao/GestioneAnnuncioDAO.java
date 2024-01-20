package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * DAO che definisce le operazioni di accesso dati per la gestione degli annunci.
 */
@Repository
public interface GestioneAnnuncioDAO extends JpaRepository<Annuncio, Long> {

    /**
     * Restituisce tutti gli annunci associati a un utente specifico.
     *
     * @param utente Utente di cui si vogliono ottenere gli annunci.
     * @return Lista di annunci associati all'utente specificato.
     */
    List<Annuncio> findByUtente(Utente utente);

    /**
     * Restituisce tutti gli annunci di una specifica categoria.
     *
     * @param categoria Categoria degli annunci desiderati.
     * @return Lista di annunci della categoria specificata.
     */
    List<Annuncio> findByCategoria(String categoria);

    /**
     * Restituisce tutti gli annunci con una specifica condizione.
     *
     * @param condizione Condizione degli annunci desiderati.
     * @return Lista di annunci con la condizione specificata.
     */
    List<Annuncio> findByCondizione(String condizione);

    /**
     * Restituisce tutti gli annunci il cui periodo di validità è compreso tra due date specificate.
     *
     * @param inizio Data di inizio del periodo di validità.
     * @param fine Data di fine del periodo di validità.
     * @return Lista di annunci con periodo di validità compreso tra le date specificate.
     */
    List<Annuncio> findByDataFineBetween(Date inizio, Date fine);

    /**
     * Restituisce tutti gli annunci che contengono una determinata descrizione.
     *
     * @param descrizione Descrizione da cercare negli annunci.
     * @return Lista di annunci che contengono la descrizione specificata.
     */
    List<Annuncio> findByDescrizioneContains(String descrizione);

    /**
     * Restituisce tutti gli annunci presenti sulla piattaforma.
     *
     * @return Lista di tutti gli annunci presenti nel sistema.
     */
    List<Annuncio> findAll();

    /**
     * Restituisce tutti gli annunci associati a utenti premium.
     *
     * @return Lista di annunci associati a utenti con stato premium.
     */
    @Query("select a from Utente u, Annuncio a WHERE u = a.utente and u.premium=true")
    List<Annuncio>findAllPremium();
}