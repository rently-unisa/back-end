package it.unisa.c02.rently.rently_application.business.gestioneNoleggio.service;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;

import java.sql.Date;
import java.util.List;

public interface GestioneNoleggioService {

    List<Noleggio> getNoleggiByNoleggiante(Utente utente);
    List<Noleggio> getNoleggiByNoleggiatore(Utente utente);
    List<Noleggio> getRichiesteByNoleggiante(Utente noleggiante);
    List<Noleggio> getRichiesteByNoleggiatore(Utente noleggiatore);
    boolean addNoleggio(Noleggio noleggio);
    boolean deleteNoleggio(Noleggio noleggio);
    Noleggio updateStatoNoleggio(Noleggio noleggio);
    List<Noleggio>  checkDisponibilita(long id_annuncio, Date inizio, Date fine);

    Noleggio getNoleggio(long id);
    List<Noleggio> checkFineNoleggio(Date dateNow);
}
