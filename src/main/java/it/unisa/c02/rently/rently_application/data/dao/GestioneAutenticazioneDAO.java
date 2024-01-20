package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO che definisce le operazioni di accesso dati per la gestione dell'autenticazione.
 */
@Repository
public interface GestioneAutenticazioneDAO extends JpaRepository<Utente, Long> {

    /**
     * Restituisce l'utente associato all'indirizzo email e alla password specificati.
     *
     * @param email Indirizzo email dell'utente.
     * @param password Password dell'utente.
     * @return Utente associato all'indirizzo email e alla password.
     */
    Utente findByEmailAndPassword(String email, String password);

    /**
     * Verifica l'esistenza di un utente nel sistema tramite indirizzo email.
     *
     * @param email Indirizzo email dell'utente da verificare.
     * @return true se esiste un utente con l'indirizzo email specificato, altrimenti false.
     */
    boolean existsByEmail(String email);

    /**
     * Verifica l'esistenza di un utente nel sistema tramite username.
     *
     * @param username Username dell'utente da verificare.
     * @return true se esiste un utente con il nome utente specificato, altrimenti false.
     */
    boolean existsByUsername(String username);
}
