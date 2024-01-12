package it.unisa.c02.rently.rently_application.business.gestioneAvvisi.service;



import it.unisa.c02.rently.rently_application.data.model.Segnalazione;

import java.util.List;

public interface GestioneAvvisiService {

    Segnalazione addSegnalazione (Segnalazione segnalazione);
    void removeSegnalazione (Segnalazione segnalazione);
    Segnalazione getSegnalazione (long id);

}
