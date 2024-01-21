package it.unisa.c02.rently.rently_application.business.gestioneNoleggio.service;

import it.unisa.c02.rently.rently_application.data.dao.GestioneNoleggioDAO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GestioneNoleggioServiceImpl implements GestioneNoleggioService {

    private final GestioneNoleggioDAO noleggioDAO;

    @Override
    public List<Noleggio> getNoleggiByNoleggiante(Utente noleggiante) {
        return noleggioDAO.findByNoleggiante(noleggiante);
    }

    @Override
    public List<Noleggio> getNoleggiByNoleggiatore(Utente noleggiatore) {
        return noleggioDAO.findByNoleggiatore(noleggiatore);
    }

    @Override
    public List<Noleggio> getRichiesteByNoleggiante(Utente noleggiante) {
        return noleggioDAO.findRichiesteByNoleggiante(noleggiante);
    }

    @Override
    public List<Noleggio> getRichiesteByNoleggiatore(Utente noleggiatore) {
        return noleggioDAO.findRichiesteByNoleggiatore(noleggiatore);
    }

    @Override
    public Noleggio addNoleggio(Noleggio noleggio) {
        return noleggioDAO.save(noleggio);
    }

    @Override
    public void deleteNoleggio(Noleggio noleggio) {
        noleggioDAO.deleteById(noleggio.getId());

    }

    @Override
    public Noleggio updateStatoNoleggio(Noleggio noleggio) {
        return noleggioDAO.save(noleggio);
    }


    @Override
    public List<Noleggio> checkDisponibilita(Annuncio annuncio, Date inizio, Date fine) {
        return noleggioDAO.checkDisponibilita(annuncio, inizio, fine);
    }

    @Override
    public Noleggio getNoleggio(long id) {
        return noleggioDAO.findById(id).orElse(null);
    }


    @Override
    public List<Noleggio> checkFineNoleggio(Date dateNow) {
        return noleggioDAO.checkFineNoleggio(dateNow);
    }

    @Override
    public List<Noleggio> findRichieste() {

        return noleggioDAO.findRichieste();
    }

}
