package it.unisa.c02.rently.rently_application.business.gestioneValutazione.controller;
import it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service.GestioneAnnuncioService;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.business.gestioneValutazione.service.GestioneValutazioneService;
import it.unisa.c02.rently.rently_application.commons.services.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.data.dto.ValutazioneDTO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneOggetto;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneUtente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/valutazione")
public class GestioneValutazioneController {

    private final GestioneValutazioneService valutazioneService;
    private final GestioneAreaPersonaleService areaPersonaleService;
    private final GestioneAnnuncioService annuncioService;
    private final ResponseService responseService;
    @PostMapping("/aggiungi-valutazione-utente")
    public ResponseEntity<String> aggiungiValutazioneUtente(@RequestBody ValutazioneDTO valutazioneDTO) {

        ValutazioneUtente valutazione = new ValutazioneUtente();
        Utente utente = areaPersonaleService.getDatiPrivati(valutazioneDTO.getValutatore());
        valutazione.setValutatore(utente);
        valutazione.setVoto(valutazioneDTO.getVoto());
        valutazione.setDescrizione(valutazioneDTO.getDescrizione());
        valutazione.setValutato(areaPersonaleService.getDatiPrivati(valutazioneDTO.getValutato()));

        if(valutazione.getValutato()!= null && valutazione.getValutatore()!= null){
            valutazione = valutazioneService.addValutazioneUtente(valutazione);
            return responseService.Ok(valutazione);
        }
        else
            return responseService.InternalError();
    }

    @GetMapping("/visualizza-valutazioni-utente")
    public ResponseEntity<String> visualizzaValutazioniUtente(@RequestParam long valutato){

        Utente utente = areaPersonaleService.getDatiPrivati(valutato);
        if(utente!= null){
            List<ValutazioneUtente> list = valutazioneService.findAllByUtente(utente);
            return responseService.Ok(list);
        }
        else
            return responseService.InternalError();
    }

    @GetMapping("/visualizza-media-valutazioni-utente")
    public ResponseEntity<String> visualizzaMediaValutazioniUtente(@RequestParam long valutato){

        Utente utente = areaPersonaleService.getDatiPrivati(valutato);
        if(utente!= null){
            double media = valutazioneService.mediaValutazioniUtenteByUtente(utente);
            return responseService.Ok(media);
        }
        else
            return responseService.InternalError();
    }

    @PostMapping("/aggiungi-valutazione-oggetto")
    public ResponseEntity<String> aggiungiValutazioneOggetto(@RequestBody ValutazioneDTO valutazioneDTO) {

        ValutazioneOggetto valutazione = new ValutazioneOggetto();
        Utente utente = areaPersonaleService.getDatiPrivati(valutazioneDTO.getValutatore());
        valutazione.setValutatore(utente);
        valutazione.setVoto(valutazioneDTO.getVoto());
        valutazione.setDescrizione(valutazioneDTO.getDescrizione());
        valutazione.setAnnuncio(annuncioService.getAnnuncio(valutazioneDTO.getValutato()).orElse(null));

        if(valutazione.getAnnuncio()!= null && valutazione.getValutatore()!= null){
            valutazione = valutazioneService.addValutazioneOggetto(valutazione);
            return responseService.Ok(valutazione);
        }
        else
            return responseService.InternalError();
    }

    @GetMapping("/visualizza-valutazioni-annuncio")
    public ResponseEntity<String> visualizzaValutazioniAnnuncio(@RequestParam long valutato){

        Annuncio annuncio = annuncioService.getAnnuncio(valutato).orElse(null);
        if(annuncio!= null){
            List<ValutazioneOggetto> list = valutazioneService.findAllByAnnuncio(annuncio);
            return responseService.Ok(list);
        }
        else
            return responseService.InternalError();
    }

    @GetMapping("/visualizza-media-valutazioni-annuncio")
    public ResponseEntity<String> visualizzaMediaValutazioniOggetto(@RequestParam long valutato){

        Annuncio annuncio = annuncioService.getAnnuncio(valutato).orElse(null);
        if(annuncio!= null){
            double media = valutazioneService.mediaValutazioniOggettoByAnnuncio(annuncio);
            return responseService.Ok(media);
        }
        else
            return responseService.InternalError();
    }


}
