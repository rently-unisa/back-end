package it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service;


import it.unisa.c02.rently.rently_application.data.dao.GestioneAreaPersonaleDAO;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementazione del servizio di gestione dell'area personale.
 * Questa classe fornisce implementazioni concrete per i metodi dichiarati nell'interfaccia GestioneAreaPersonaleService.
 */
@Service
@RequiredArgsConstructor
public class GestioneAreaPersonaleServiceImpl implements GestioneAreaPersonaleService {

    /**
     * Istanza di GestioneAreaPersonaleDAO utilizzata per l'accesso ai dati degli annunci.
     */
    private final GestioneAreaPersonaleDAO gestioneAreaPersonaleDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public Utente updateUtente(Utente utente) {
        return gestioneAreaPersonaleDAO.save(utente);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Utente getDatiPrivati(long id) {
        Optional<Utente> optional = gestioneAreaPersonaleDAO.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        else
            return null;
    }

    /**
     * {@inheritDoc}
     */
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
