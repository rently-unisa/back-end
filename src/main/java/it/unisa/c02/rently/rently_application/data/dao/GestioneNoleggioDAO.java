package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * DAO che definisce le operazioni di accesso dati per la gestione dei noleggi.
 */
@Repository
public interface GestioneNoleggioDAO extends JpaRepository<Noleggio, Long> {

    /**
     * Recupera i noleggi associati a un determinato noleggiante.
     *
     * @param noleggiante Utente che ha preso a noleggio un oggetto.
     * @return Lista di noleggi associati al noleggiante specificato.
     */

    @Query("select n from Noleggio n WHERE n.noleggiante = ?1 and (n.stato != 'RICHIESTA' AND n.stato != 'ACCETTATA' AND n.stato != 'RIFIUTATA')")
    List<Noleggio> findByNoleggiante(Utente noleggiante);

    /**
     * Recupera i noleggi associati a un determinato noleggiatore.
     *
     * @param noleggiatore L'utente che ha dato a noleggio un oggetto.
     * @return Lista di noleggi associati al noleggiatore specificato.
     */
    @Query("select n from Noleggio n WHERE n.noleggiatore = ?1 and (n.stato != 'RICHIESTA' AND n.stato != 'ACCETTATA' AND n.stato != 'RIFIUTATA')")
    List<Noleggio> findByNoleggiatore(Utente noleggiatore);

    /**
     * Recupera i noleggi in stato 'RICHIESTA' e 'RIFIUTATA'.
     *
     * @return Lista di noleggi in stato 'RICHIESTA'.
     */
    @Query("select n from Noleggio n where (n.stato = 'RICHIESTA' and n.stato = 'RIFIUTATA')")
    List<Noleggio> findRichieste();

    /**
     * Recupera i noleggi in stato 'RICHIESTA' associati a un determinato noleggiante.
     *
     * @param noleggiante Utente che ha effettuato la richiesta di noleggio.
     * @return Lista di noleggi in stato 'RICHIESTA' associati al noleggiante specificato.
     */
    @Query("select n from Noleggio n WHERE n.noleggiante = ?1 and (n.stato = 'RICHIESTA' OR n.stato = 'ACCETTATA' OR n.stato = 'RIFIUTATA')")
    List<Noleggio> findRichiesteByNoleggiante(Utente noleggiante);

    /**
     * Recupera i noleggi in stato 'RICHIESTA' associati a un determinato noleggiatore.
     *
     * @param noleggiatore Utente che ha ricevuto la richiesta di noleggio.
     * @return Lista di noleggi in stato 'RICHIESTA' associati al noleggiatore specificato.
     */
    @Query("select n from Noleggio n WHERE n.noleggiatore = ?1 and(n.stato = 'RICHIESTA' OR n.stato = 'ACCETTATA' OR n.stato = 'RIFIUTATA')")
    List<Noleggio> findRichiesteByNoleggiatore(Utente noleggiatore);

    /**
     * Verifica la disponibilità di un annuncio in un determinato periodo di tempo.
     *
     * @param annuncio Identificativo dell'annuncio.
     * @param data_inizio Data di inizio del periodo di noleggio.
     * @param data_fine Data di fine del periodo di noleggio.
     * @return Lista di noleggi che interferiscono con il periodo specificato.
     */
    @Query("SELECT n FROM Noleggio n where (n.annuncio=?1) and ((n.dataInizio>=?2 and n.dataInizio<=?3) or (n.dataFine<= ?3 and n.dataFine>=?2 )) and (n.stato != 'RIFIUTATA' and n.stato != 'CONCLUSO' AND n.stato != 'RICHIESTA')")
    List<Noleggio> checkDisponibilita (Annuncio annuncio, Date data_inizio, Date data_fine);

    /**
     * Verifica la presenza di noleggi in corso la cui data di fine è precedente o uguale alla data attuale.
     *
     * @param dateNow Data attuale.
     * @return Lista di noleggi in corso che dovrebbero essere conclusi.
     */
    @Query("SELECT t FROM Noleggio t where (t.stato = 'IN_CORSO') and (t.dataFine <= ?1)")
    List<Noleggio> checkFineNoleggio (Date dateNow);
}
