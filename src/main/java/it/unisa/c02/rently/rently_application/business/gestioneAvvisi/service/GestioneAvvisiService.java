package it.unisa.c02.rently.rently_application.business.gestioneAvvisi.service;
import it.unisa.c02.rently.rently_application.data.model.Segnalazione;


/**
 * Questa interfaccia definisce le specifiche per i servizi dedicati alla gestione degli avvisi.
 */
public interface GestioneAvvisiService {

    /**
     * Aggiunge una segnalazione sulla piattaforma.
     *
     * @param segnalazione Segnalazione da aggiungere.
     * @return Segnalazione aggiunta.
     */

    Segnalazione addSegnalazione (Segnalazione segnalazione);

    /**
     * Elimina una segnalazione dalla piattaforma.
     *
     * @param segnalazione Segnalazione da eliminare.
     *
     */
    void removeSegnalazione (Segnalazione segnalazione);

    /**
     * Restituisce una Segnalazione specifica in base all'identificativo.
     *
     * @param id Identificativo della segnalazione.
     * @return La segnalazione se esiste, null altrimenti.
     */
    Segnalazione getSegnalazione (long id);

}
