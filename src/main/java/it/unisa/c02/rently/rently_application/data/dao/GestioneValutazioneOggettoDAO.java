package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneOggetto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO che definisce le operazioni di accesso dati per la gestione delle valutazioni di oggetti.
 */
@Repository
public interface GestioneValutazioneOggettoDAO extends JpaRepository<ValutazioneOggetto, Long> {

    /**
     * Recupera le valutazioni associate a un annuncio specifico.
     *
     * @param annuncio Annuncio di cui si vogliono ottenere le valutazioni.
     * @return Lista di valutazioni associate all'annuncio specificato.
     */
    List<ValutazioneOggetto> findByAnnuncio(Annuncio annuncio);

    /**
     * Calcola la media delle valutazioni di un oggetto identificato da un annuncio.
     *
     * @param annuncio Identificativo dell'annuncio associato all'oggetto valutato.
     * @return Media delle valutazioni dell'oggetto, espresso come valore decimale.
     */
    @Query("select AVG(v.voto) from ValutazioneOggetto v where v.annuncio = ?1")
    double mediaValutazioniOggettoById(long annuncio);

    /**
     * Verifica se esiste una valutazione associata a un annuncio di un noleggio specifico.
     *
     * @param idNoleggio Identificativo del noleggio.
     * @return Valutazione associata al noleggio, se presente.
     */
    @Query("select vo from Noleggio n, ValutazioneOggetto vo where n.id=?1 and n = vo.noleggio and n.annuncio = vo.annuncio and n.noleggiante = vo.valutatore")
    ValutazioneOggetto valutazioneAnnuncioIsPresent(long idNoleggio);
}
