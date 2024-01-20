package it.unisa.c02.rently.rently_application.business.gestioneRicerca.service;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;

import java.util.Date;
import java.util.List;

/**
 * Questa interfaccia definisce le specifiche per i servizi dedicati alla gestione della ricerca di annunci.
 */
public interface GestioneRicercaService {

    /**
     * Restituisce una lista di annunci che corrispondono alla categoria specificata.
     *
     * @param categoria Categoria degli annunci da cercare.
     * @return Lista di annunci che appartengono alla categoria specificata.
     */
    List<Annuncio> searchByCategoria(String categoria);

    /**
     * Restituisce una lista di annunci che corrispondono alla condizione specificata.
     *
     * @param condizione Condizione degli annunci da cercare.
     * @return Lista di annunci che soddisfano la condizione specificata.
     */
    List<Annuncio> searchByCondizione(String condizione);

    /**
     * Restituisce una lista di annunci che sono pubblicati tra le date specificate.
     *
     * @param inizio Data di inizio periodo di ricerca.
     * @param fine   Data di fine periodo di ricerca.
     * @return Lista di annunci pubblicati tra le date specificate.
     */
    List<Annuncio> searchByData(Date inizio, Date fine);

    /**
     * Restituisce una lista di annunci che contengono la descrizione specificata.
     *
     * @param descrizione Descrizione da cercare negli annunci.
     * @return Lista di annunci che contengono la descrizione specificata.
     */
    List<Annuncio> searchByDescrizione(String descrizione);

    /**
     * Restituisce una lista di tutti gli annunci presenti nel sistema.
     *
     * @return Lista di tutti gli annunci presenti sulla piattaforma.
     */
    List<Annuncio> searchAll();

    /**
     * Restituisce una lista di annunci di utenti premium.
     *
     * @return Lista di annunci di utenti premium presenti sulla piattaforma.
     */
    List<Annuncio> searchAnnunciPremium();
}
