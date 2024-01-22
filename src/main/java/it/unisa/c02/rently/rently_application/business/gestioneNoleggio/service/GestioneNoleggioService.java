package it.unisa.c02.rently.rently_application.business.gestioneNoleggio.service;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;

import java.sql.Date;
import java.util.List;

/**
 * Questa interfaccia definisce le specifiche per i servizi dedicati alla gestione dei noleggi.
 */
public interface GestioneNoleggioService {

    /**
     * Restituisce tutti i noleggi, eccetto le richieste, effettuati da un noleggiante.
     *
     * @param utente l'Utente che rappresenta il noleggiante.
     * @return lista di noleggi effettuati dallo specifico noleggiante.
     */
    List<Noleggio> getNoleggiByNoleggiante(Utente utente);

    /**
     * Restituisce tutti i noleggi, eccetto le richieste, effettuati da un noleggiatore.
     *
     * @param utente l'Utente che rappresenta il noleggiatore.
     * @return lista di noleggi effettuati dallo specifico noleggiatore.
     */
    List<Noleggio> getNoleggiByNoleggiatore(Utente utente);

    /**
     * Restituisce tutte le richieste, anche in stato RIFIUTATA e ACCETTATA, effettuate da un noleggiante.
     *
     * @param noleggiante l'Utente che rappresenta il noleggiante.
     * @return lista di richieste effettuati dallo specifico noleggiante.
     */
    List<Noleggio> getRichiesteByNoleggiante(Utente noleggiante);

    /**
     * Restituisce tutte le richieste, anche in stato RIFIUTATA e ACCETTATA, effettuate da un noleggiatore.
     *
     * @param noleggiatore l'Utente che rappresenta il noleggiatore.
     * @return lista di richieste effettuati dallo specifico noleggiatore.
     */
    List<Noleggio> getRichiesteByNoleggiatore(Utente noleggiatore);

    /**
     * Aggiunge un nuovo Noleggio alla piattaforma.
     *
     * @param noleggio Il Noleggio da aggiungere.
     * @return Noleggio aggiunto.
     */
    Noleggio addNoleggio(Noleggio noleggio);

    /**
     * Elimina un Noleggio dalla piattaforma.
     *
     * @param noleggio Il Noleggio da eliminare.
     */
    void deleteNoleggio(Noleggio noleggio);

    /**
     * Restituisce un Noleggio con stato cambiato.
     *
     * @param noleggio il Noleggio di cui si vuole modificare lo stato.
     * @return Noleggio con stato modificato.
     */
    Noleggio updateStatoNoleggio(Noleggio noleggio);

    /**
     * Verifica la disponibilità di un annuncio in un determinato periodo di tempo.
     *
     * @param annuncio l'annuncio di cui si vuole controllare la disponibilità..
     * @param inizio Data di inizio del periodo di noleggio.
     * @param fine Data di fine del periodo di noleggio.
     * @return Lista di noleggi che interferiscono con il periodo specificato.
     */
    List<Noleggio> checkDisponibilita(Annuncio annuncio, Date inizio, Date fine);

    /**
     * Restituisce tutti i noleggi in stato 'RICHIESTA' e 'RIFIUTATA'.
     *
     *
     * @return Noleggio con stato modificato.
     */
    List<Noleggio> findRichieste ();

    /**
     * Restituisce i dati del noleggio identificato dal suo ID.
     *
     * @param id l'id del noleggio
     * @return Noleggio con stato modificato.
     */
    Noleggio getNoleggio(long id);

    /**
     * Verifica la presenza di noleggi in corso la cui data di fine è precedente o uguale alla data attuale.
     *
     * @param dateNow Data attuale.
     * @return Lista di noleggi in corso che dovrebbero essere conclusi.
     */
    List<Noleggio> checkFineNoleggio(Date dateNow);
}
