package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface GestioneNoleggioDAO extends JpaRepository<Noleggio, Long> {


    List<Noleggio> findByNoleggiante(Utente noleggiante);
    List<Noleggio> findByNoleggiatore(Utente noleggiatore);
    @Query("select n from Noleggio n where n.stato = 'RICHIESTA'")
    List<Noleggio> findRichieste();
    @Query("select n from Utente u, Noleggio n WHERE u = n.noleggiante and n.stato = 'RICHIESTA'")
    List<Noleggio> findRichiesteByNoleggiante(Utente noleggiante);
    @Query("select n from Utente u, Noleggio n WHERE u = n.noleggiatore and n.stato = 'RICHIESTA'")
    List<Noleggio> findRichiesteByNoleggiatore(Utente noleggiatore);
    @Query("SELECT n FROM Noleggio n where (n.annuncio=?1) and ((n.dataInizio>=?2 and n.dataInizio<=?3) or (n.dataFine<= ?3 and n.dataFine>=?2 )) and n.stato != 'CONCLUSO'")
    List<Noleggio> checkDisponibilita (long id_annuncio, Date data_inizio, Date data_fine);

    @Query("SELECT t FROM Noleggio t where (t.stato = 'IN_CORSO') and (t.dataFine <= ?1)")
    List<Noleggio> checkFineNoleggio (Date dateNow);

}
