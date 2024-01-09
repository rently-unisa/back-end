package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GestioneAutenticazioneDAO extends JpaRepository<Utente, Long> {

    Utente findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
