package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Segnalazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO che definisce le operazioni di accesso dati per la gestione degli avvisi.
 */
@Repository
public interface GestioneAvvisiSegnalazioneDAO extends JpaRepository<Segnalazione, Long> {

}
