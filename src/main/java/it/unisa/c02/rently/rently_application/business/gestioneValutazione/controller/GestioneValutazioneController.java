package it.unisa.c02.rently.rently_application.business.gestioneValutazione.controller;
import it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service.GestioneAnnuncioService;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.business.gestioneValutazione.service.GestioneValutazioneService;
import it.unisa.c02.rently.rently_application.data.DTO.ValutazioneDTO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneOggetto;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneUtente;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/valutazione")
public class GestioneValutazioneController {

    private final GestioneValutazioneService valutazioneService;
    private final GestioneAreaPersonaleService areaPersonaleService;
    private final GestioneAnnuncioService annuncioService;
    @PostMapping("/aggiungi-valutazione-utente")
    public ValutazioneUtente aggiungiValutazioneUtente(@RequestBody ValutazioneDTO valutazioneDTO) {

        //prendo l'utente dalla sessione con Principal
        ValutazioneUtente valutazione = new ValutazioneUtente();
        valutazione.setVoto(valutazioneDTO.getVoto());
        valutazione.setDescrizione(valutazioneDTO.getDescrizione());
        valutazione.setValutato(areaPersonaleService.getDatiPrivati(valutazioneDTO.getValutato()));
        //set del valutatore con l'utente della sessione

        if(valutazione.getValutato()!= null && valutazione.getValutatore()!= null){
            return valutazioneService.addValutazioneUtente(valutazione);
        }
        else
            return null;
    }

    @GetMapping("/visualizza-valutazioni-utente")
    public List<ValutazioneUtente> visualizzaValutazioniUtente(@RequestParam long valutato){

        Utente utente = areaPersonaleService.getDatiPrivati(valutato);
        if(utente!= null){
            return valutazioneService.findAllByUtente(utente);
        }
        else
            return null;
    }

    @GetMapping("/visualizza-media-valutazioni-utente")
    public double visualizzaMediaValutazioniUtente(@RequestParam long valutato){

        Utente utente = areaPersonaleService.getDatiPrivati(valutato);
        if(utente!= null){
            return valutazioneService.mediaValutazioniUtenteByUtente(utente);
        }
        else
            return 0;
    }

    @PostMapping("/aggiungi-valutazione-oggetto")
    public ValutazioneOggetto aggiungiValutazioneOggetto(@RequestBody ValutazioneDTO valutazioneDTO) {

        //prendo l'utente dalla sessione con Principal
        ValutazioneOggetto valutazione = new ValutazioneOggetto();
        valutazione.setVoto(valutazioneDTO.getVoto());
        valutazione.setDescrizione(valutazioneDTO.getDescrizione());
        valutazione.setAnnuncio(annuncioService.getAnnuncio(valutazioneDTO.getValutato()).orElse(null));
        //set del valutatore con l'utente della sessione

        if(valutazione.getAnnuncio()!= null && valutazione.getValutatore()!= null){
            return valutazioneService.addValutazioneOggetto(valutazione);
        }
        else
            return null;
    }

    @GetMapping("/visualizza-valutazioni-annuncio")
    public List<ValutazioneOggetto> visualizzaValutazioniAnnuncio(@RequestParam long valutato){

        Annuncio annuncio = annuncioService.getAnnuncio(valutato).orElse(null);
        if(annuncio!= null){
            return valutazioneService.findAllByAnnuncio(annuncio);
        }
        else
            return null;
    }

    @GetMapping("/visualizza-media-valutazioni-annuncio")
    public double visualizzaMediaValutazioniOggetto(@RequestParam long valutato){

        Annuncio annuncio = annuncioService.getAnnuncio(valutato).orElse(null);
        if(annuncio!= null){
            return valutazioneService.mediaValutazioniOggettoByAnnuncio(annuncio);
        }
        else
            return 0;
    }


}
