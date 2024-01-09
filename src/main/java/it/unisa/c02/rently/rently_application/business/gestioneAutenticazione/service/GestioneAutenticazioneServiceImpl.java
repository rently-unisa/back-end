package it.unisa.c02.rently.rently_application.business.gestioneAutenticazione.service;

import it.unisa.c02.rently.rently_application.commons.psw.PswCoder;
import it.unisa.c02.rently.rently_application.data.dao.GestioneAutenticazioneDAO;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class GestioneAutenticazioneServiceImpl implements GestioneAutenticazioneService {

    private final GestioneAutenticazioneDAO autenticazioneDAO;
    private final PswCoder pswCoder;


    @Override
    public Utente login(String email, String password) {
        return autenticazioneDAO.findByEmailAndPassword(email, password);
    }

    @Override
    public boolean checkEmail(String email) {
        return autenticazioneDAO.existsByEmail(email);
    }

    @Override
    public boolean checkUsername(String username) {
        return autenticazioneDAO.existsByUsername(username);
    }

    @Override
    public void signUp(Utente utente) throws NoSuchAlgorithmException {
        if (!checkEmail(utente.getEmail()) && !checkUsername(utente.getUsername())) {
            utente.setPassword(pswCoder.codificaPassword(utente.getPassword()));
            autenticazioneDAO.save(utente);
        }
    }
}
