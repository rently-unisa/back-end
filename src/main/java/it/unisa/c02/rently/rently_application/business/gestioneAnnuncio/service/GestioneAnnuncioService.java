package it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Utente;

import java.util.List;
import java.util.Optional;

public interface GestioneAnnuncioService {

    Optional<Annuncio> getAnnuncio(long id);
    Annuncio updateAnnuncio(Annuncio annuncio);
    Annuncio addAnnuncio(Annuncio annuncio);
    boolean deleteAnnuncio(Long id);
    List<Annuncio> findAllByUtente(Utente utente);
}
