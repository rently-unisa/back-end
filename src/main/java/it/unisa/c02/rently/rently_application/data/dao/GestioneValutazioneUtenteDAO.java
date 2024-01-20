package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Utente;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneUtente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO che definisce le operazioni di accesso dati per la gestione delle valutazioni di utenti.
 */
@Repository
public interface GestioneValutazioneUtenteDAO extends JpaRepository<ValutazioneUtente, Long> {

    /**
     * Recupera le valutazioni associate a un utente specifico.
     *
     * @param valutato Utente di cui si vogliono ottenere le valutazioni.
     * @return Lista di valutazioni associate all'utente specificato.
     */
    List<ValutazioneUtente> findByValutato(Utente valutato);

    /**
     * Calcola la media delle valutazioni di un utente identificato dal suo ID.
     *
     * @param valutato Identificativo dell'utente.
     * @return Media delle valutazioni dell'utente, espresso come valore decimale.
     */
    @Query("select AVG(v.voto) from ValutazioneUtente v where v.valutato = ?1")
    double mediaValutazioniUtenteById(long valutato);

    /**
     * Verifica se esiste una valutazione associata al noleggiante in un noleggio specifico.
     *
     * @param idNoleggio Identificativo del noleggio.
     * @return Valutazione associata al noleggiante, se presente.
     */
    @Query("select vu from Noleggio n, ValutazioneUtente vu where n.id =?1 and n = vu.noleggio and n.noleggiante = vu.valutato and  n.noleggiatore = vu.valutatore")
    ValutazioneUtente valutazioneNoleggianteIsPresent(long idNoleggio);

    /**
     * Verifica se esiste una valutazione associata al noleggiatore in un noleggio specifico.
     *
     * @param idNoleggio Identificativo del noleggio.
     * @return Valutazione associata al noleggiatore, se presente.
     */
    @Query("select vu from Noleggio n, ValutazioneUtente vu where n.id =?1 and n = vu.noleggio and n.noleggiante = vu.valutatore and  n.noleggiatore = vu.valutato")
    ValutazioneUtente valutazioneNoleggiatoreIsPresent(long idNoleggio);
}
