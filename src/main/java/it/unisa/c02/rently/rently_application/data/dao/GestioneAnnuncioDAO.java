package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GestioneAnnuncioDAO extends JpaRepository<Annuncio, Long> {

    List<Annuncio> findByUtente(Utente utente);
    List<Annuncio> findByCategoria(String categoria);
    List<Annuncio> findByCondizione(String condizione);
    List<Annuncio> findByDataFineBetween(Date inizio, Date fine);

    List<Annuncio> findByDescrizioneContains(String descrizione);

    List<Annuncio> findAll();
    @Query("select a from Utente u, Annuncio a WHERE u = a.utente and u.premium=true")
    List<Annuncio>findAllPremium();
}
