package it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service;


import it.unisa.c02.rently.rently_application.data.dao.GestioneAreaPersonaleDAO;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GestioneAreaPersonaleServiceImpl implements GestioneAreaPersonaleService {

    private final GestioneAreaPersonaleDAO gestioneAreaPersonaleDAO;


    @Override
    public Utente updateUtente(Utente utente) {
        return gestioneAreaPersonaleDAO.save(utente);
    }

    @Override
    public Utente getDatiPrivati(long id) {
        Optional<Utente> optional = gestioneAreaPersonaleDAO.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        else
            return null;
    }

    @Override
    public Utente findByUsername(Utente utente) {
        Optional<Utente> optional = gestioneAreaPersonaleDAO.findByUsername(utente.getUsername());
        if(optional.isPresent()) {
            return optional.get();
        }
        else
            return null;
    }


}
