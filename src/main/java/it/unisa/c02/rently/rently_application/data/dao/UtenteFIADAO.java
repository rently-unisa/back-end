package it.unisa.c02.rently.rently_application.data.dao;


import it.unisa.c02.rently.rently_application.data.model.Utente;
import it.unisa.c02.rently.rently_application.data.model.UtenteFIA;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneUtente;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtenteFIADAO extends JpaRepository<UtenteFIA, Long> {

    UtenteFIA findByIdUtente(Utente u);
}
