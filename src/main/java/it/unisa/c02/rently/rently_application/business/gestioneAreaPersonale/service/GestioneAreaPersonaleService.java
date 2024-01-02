package it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service;

import it.unisa.c02.rently.rently_application.data.DTO.UtenteDTO;
import it.unisa.c02.rently.rently_application.data.model.Utente;

public interface GestioneAreaPersonaleService {

    Utente updateUtente(Utente utente);
    UtenteDTO getDatiPrivati(long id);

}
