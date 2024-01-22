package it.unisa.c02.rently.rently_application.business.gestioneAutenticazione.service;

import it.unisa.c02.rently.rently_application.data.dao.GestioneAutenticazioneDAO;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * Implementazione del servizio di gestione dell'autenticazione.
 * Questa classe fornisce implementazioni concrete per i metodi dichiarati nell'interfaccia GestioneAutenticazioneService.
 */
@Service
@RequiredArgsConstructor
public class GestioneAutenticazioneServiceImpl implements GestioneAutenticazioneService {

    /**
     * Istanza di GestioneAutenticazioneDAO utilizzata per l'accesso ai dati degli utenti.
     */
    private final GestioneAutenticazioneDAO autenticazioneDAO;


    /**
     * {@inheritDoc}
     */
    @Override
    public Utente login(String email, String password) {
        return autenticazioneDAO.findByEmailAndPassword(email, password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkEmail(String email) {
        return autenticazioneDAO.existsByEmail(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkUsername(String username) {
        return autenticazioneDAO.existsByUsername(username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void signUp(Utente utente) throws NoSuchAlgorithmException {
        if (!checkEmail(utente.getEmail()) && !checkUsername(utente.getUsername())) {
            utente.setPassword(utente.getPassword());
            autenticazioneDAO.save(utente);
        }
    }
}
