package it.unisa.c02.rently.rently_application.business.gestioneRicerca.service;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;

import java.util.Date;
import java.util.List;

public interface GestioneRicercaService {

    List<Annuncio> searchByCategoria(String categoria);
    List<Annuncio> searchByCondizione(String condizione);
    List<Annuncio> searchByData(Date inizio, Date fine);
    List<Annuncio> searchByDescrizione(String descrizione);
    List<Annuncio> searchAll();
    List<Annuncio> searchAnnunciPremium();
}
