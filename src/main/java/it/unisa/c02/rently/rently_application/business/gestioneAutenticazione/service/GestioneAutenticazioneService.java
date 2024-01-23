package it.unisa.c02.rently.rently_application.business.gestioneAutenticazione.service;

import it.unisa.c02.rently.rently_application.data.model.Utente;

import java.security.NoSuchAlgorithmException;

/**
 * Questa interfaccia definisce le specifiche per i servizi dedicati alla gestione dell'autenticazione.
 */
public interface GestioneAutenticazioneService {

    /**
     * Verifica l'esistenza di un utente nel sistema tramite indirizzo email.
     *
     * @param email Indirizzo email dell'utente da verificare.
     * @return true se esiste un utente con l'indirizzo email specificato, altrimenti false.
     */
    boolean checkEmail(String email);

    /**
     * Verifica l'esistenza di un utente nel sistema tramite username.
     *
     * @param username Username dell'utente da verificare.
     * @return true se esiste un utente con il nome utente specificato, altrimenti false.
     */
    boolean checkUsername(String username);

    /**
     * Aggiunge un nuovo Utente sulla piattaforma se non ne esiste un altro con lo stesso username e la stessa email.
     *
     * @param utente Utente da aggiungere alla piattaforma.
     */
    void signUp(Utente utente) throws NoSuchAlgorithmException;

    /**
     * Restituisce l'utente associato all'indirizzo email e alla password specificati.
     *
     * @param email Indirizzo email dell'utente.
     * @param password Password dell'utente.
     * @return Utente associato all'indirizzo email e alla password.
     */
    Utente login(String email, String password);
}
