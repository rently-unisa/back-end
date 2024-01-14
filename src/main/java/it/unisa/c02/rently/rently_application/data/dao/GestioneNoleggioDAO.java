package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GestioneNoleggioDAO extends JpaRepository<Noleggio, Long> {

    Noleggio findById(long id);
    List<Noleggio> findByNoleggiante(Utente noleggiante);
    List<Noleggio> findByNoleggiatore(Utente noleggiatore);
    @Query("select n from Utente u, Noleggio n WHERE u = n.noleggiante and n.stato = 'RICHIESTA'")
    List<Noleggio> findRichiesteByNoleggiante(Utente noleggiante);
    @Query("select n from Utente u, Noleggio n WHERE u = n.noleggiatore and n.stato = 'RICHIESTA'")
    List<Noleggio> findRichiesteByNoleggiatore(Utente noleggiatore);
}
