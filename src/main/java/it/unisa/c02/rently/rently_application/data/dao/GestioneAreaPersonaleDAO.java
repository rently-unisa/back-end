package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * DAO che definisce le operazioni di accesso dati per la gestione dell'area personale.
 */
@Repository
public interface GestioneAreaPersonaleDAO extends JpaRepository<Utente, Long> {

    /**
     * Restituisce un utente in base al suo username.
     *
     * @param username Il nome utente dell'utente cercato.
     * @return Optional contenente l'utente trovato, se presente.
     *         Se l'utente non esiste, l'Optional sarà vuoto.
     */
    Optional<Utente> findByUsername(String username);
}
