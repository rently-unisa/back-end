package it.unisa.c02.rently.rently_application.business.gestioneAvvisi.service;


import it.unisa.c02.rently.rently_application.data.dao.GestioneAvvisiSegnalazioneDAO;
import it.unisa.c02.rently.rently_application.data.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class GestioneAvvisiServiceImpl implements GestioneAvvisiService {

    private final GestioneAvvisiSegnalazioneDAO segnalazioneDAO;


    @Override
    public Segnalazione addSegnalazione(Segnalazione segnalazione) {
        return segnalazioneDAO.save(segnalazione);
    }

    @Override
    public void removeSegnalazione(Segnalazione segnalazione) {
        segnalazioneDAO.deleteById(segnalazione.getId());
    }

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
