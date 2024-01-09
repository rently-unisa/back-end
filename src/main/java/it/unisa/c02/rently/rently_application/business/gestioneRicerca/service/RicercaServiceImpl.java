package it.unisa.c02.rently.rently_application.business.gestioneRicerca.service;

import it.unisa.c02.rently.rently_application.data.dao.GestioneAnnuncioDAO;
import it.unisa.c02.rently.rently_application.data.dao.GestioneAnnuncioDAO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RicercaServiceImpl implements RicercaService {

    private final GestioneAnnuncioDAO annuncioDAO;

    @Override
    public List<Annuncio> searchByCategoria(String categoria) {
        return annuncioDAO.findByCategoria(categoria);
    }

    @Override
    public List<Annuncio> searchByCondizione(String condizione) {
        return annuncioDAO.findByCondizione(condizione);
    }

    @Override
    public List<Annuncio> searchByData(Date inizio, Date fine) {
        return annuncioDAO.findByDataBetween(inizio, fine);
    }

    @Override
    public List<Annuncio> searchByDescrizione(String descrizione) {
        return annuncioDAO.findByDescrizioneContains(descrizione);
    }

    @Override
    public List<Annuncio> searchAll() {
        return annuncioDAO.findAll();
    }

    @Override
    public List<Annuncio> searchAnnunciPremium() {
        return annuncioDAO.findByPremiumTrue();
    }
}

