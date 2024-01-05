package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Utente;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneUtente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GestioneValutazioneUtenteDAO extends JpaRepository<ValutazioneUtente, Long> {


    List<ValutazioneUtente> findByValutato(Utente valutato);
    @Query("select AVG(v.voto) from ValutazioneUtente v where v.valutato = ?1")
    double mediaValutazioniUtenteById(long valutato);
}
