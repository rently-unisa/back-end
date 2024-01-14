package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Segnalazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface GestioneAvvisiSegnalazioneDAO extends JpaRepository<Segnalazione, Long> {

}
