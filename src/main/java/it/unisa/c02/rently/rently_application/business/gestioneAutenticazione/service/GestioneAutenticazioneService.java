package it.unisa.c02.rently.rently_application.business.gestioneAutenticazione.service;

import it.unisa.c02.rently.rently_application.data.model.Utente;

import java.security.NoSuchAlgorithmException;

public interface GestioneAutenticazioneService {

    boolean checkEmail(String email);

    boolean checkUsername(String username);

    void signUp(Utente utente) throws NoSuchAlgorithmException;

    Utente login(String email, String password);
}
