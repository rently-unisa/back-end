package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestioneAreaPersonaleDAO extends JpaRepository<Utente, Long> {
}
