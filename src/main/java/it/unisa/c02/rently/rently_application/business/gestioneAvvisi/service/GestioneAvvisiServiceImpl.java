package it.unisa.c02.rently.rently_application.business.gestioneAvvisi.service;


import it.unisa.c02.rently.rently_application.data.dao.GestioneAvvisiSegnalazioneDAO;
import it.unisa.c02.rently.rently_application.data.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Implementazione del servizio di gestione degli avvisi.
 * Questa classe fornisce implementazioni concrete per i metodi dichiarati nell'interfaccia GestioneAvvisiService.
 */
@Service
@RequiredArgsConstructor
public class GestioneAvvisiServiceImpl implements GestioneAvvisiService {

    /**
     * L'istanza di GestioneAvvisiSegnalazioneDAO utilizzata per l'accesso ai dati delle segnalalzioni.
     */
    private final GestioneAvvisiSegnalazioneDAO segnalazioneDAO;


    /**
     * {@inheritDoc}
     */
    @Override
    public Segnalazione addSegnalazione(Segnalazione segnalazione) {
        return segnalazioneDAO.save(segnalazione);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSegnalazione(Segnalazione segnalazione) {
        segnalazioneDAO.deleteById(segnalazione.getId());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Segnalazione getSegnalazione(long id) {
        Optional<Segnalazione> optional = segnalazioneDAO.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        else
            return null;
    }
}
