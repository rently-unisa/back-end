package it.unisa.c02.rently.rently_application.business.gestioneValutazione.service;


import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.data.dao.GestioneAreaPersonaleDAO;
import it.unisa.c02.rently.rently_application.data.dao.GestioneValutazioneOggettoDAO;
import it.unisa.c02.rently.rently_application.data.dao.GestioneValutazioneUtenteDAO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneOggetto;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneUtente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GestioneValutazioneServiceImpl implements GestioneValutazioneService {

    private final GestioneValutazioneUtenteDAO valutazioneUtenteDAO;
    private final GestioneValutazioneOggettoDAO valutazioneOggettoDAO;
    @Override
    public ValutazioneUtente addValutazioneUtente (ValutazioneUtente valutazione){
        return valutazioneUtenteDAO.save(valutazione);
    }

    @Override
    public void deleteValutazioneUtente(ValutazioneUtente valutazione) {
        valutazioneUtenteDAO.deleteById(valutazione.getId());
    }

    @Override
    public List<ValutazioneUtente> findAllByUtente(Utente valutato) {
        return valutazioneUtenteDAO.findByValutato(valutato);
    }

    @Override
    public double mediaValutazioniUtenteByUtente(Utente valutato) {
        return (double)valutazioneUtenteDAO.mediaValutazioniUtenteById(valutato.getId());
    }

    @Override
    public ValutazioneOggetto addValutazioneOggetto(ValutazioneOggetto valutazione) {
        return valutazioneOggettoDAO.save(valutazione);
    }

    @Override
    public void deleteValutazioneOggetto(ValutazioneOggetto valutazione) {
        valutazioneOggettoDAO.deleteById(valutazione.getId());
    }

    @Override
    public List<ValutazioneOggetto> findAllByAnnuncio(Annuncio annuncio) {
        return valutazioneOggettoDAO.findByAnnuncio(annuncio);
    }

    @Override
    public double mediaValutazioniOggettoByAnnuncio(Annuncio annuncio) {
        return (double)valutazioneOggettoDAO.mediaValutazioniOggettoById(annuncio.getId());
    }


}
