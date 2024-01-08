package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneOggetto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GestioneValutazioneOggettoDAO extends JpaRepository<ValutazioneOggetto, Long> {

    List<ValutazioneOggetto> findByAnnuncio(Annuncio annuncio);

    @Query("select AVG(v.voto) from ValutazioneOggetto v where v.annuncio = ?1")
    double mediaValutazioniOggettoById(long annuncio);
}
