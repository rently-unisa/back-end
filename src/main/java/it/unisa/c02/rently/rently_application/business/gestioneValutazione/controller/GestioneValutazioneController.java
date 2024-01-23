package it.unisa.c02.rently.rently_application.business.gestioneValutazione.controller;
import it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service.GestioneAnnuncioService;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.business.gestioneNoleggio.service.GestioneNoleggioService;
import it.unisa.c02.rently.rently_application.business.gestioneValutazione.service.GestioneValutazioneService;
import it.unisa.c02.rently.rently_application.commons.services.regexService.RegexTester;
import it.unisa.c02.rently.rently_application.commons.services.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.data.dto.ResponseDTO;
import it.unisa.c02.rently.rently_application.data.dto.ValutazioneDTO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneOggetto;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneUtente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Questa classe gestisce le richieste relative alle valutazioni degli utenti e degli oggetti attraverso i servizi forniti da
 * GestioneValutazioneService, GestioneAreaPersonaleService, GestioneAnnuncioService e GestioneNoleggioService.
 * Fornisce endpoint RESTful per aggiungere, visualizzare e calcolare medie delle valutazioni.
 * Le risposte sono gestite utilizzando il servizio ResponseService per costruire risposte standardizzate in formato JSON.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/valutazione")
@CrossOrigin(
        origins = {
                "*",
        },
        methods = {
                RequestMethod.OPTIONS,
                RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.POST
        })
public class GestioneValutazioneController {

    /**
     * Service per effettuare le operazioni di persistenza.
     */
    private final GestioneValutazioneService valutazioneService;

    /**
     * Service per effettuare le operazioni di persistenza.
     */
    private final GestioneAreaPersonaleService areaPersonaleService;

    /**
     * Service per effettuare le operazioni di persistenza.
     */
    private final GestioneAnnuncioService annuncioService;

    /**
     * Service per la gestione delle risposte alle richieste.
     */
    private final ResponseService responseService;

    /**
     * Service per effettuare le operazioni di persistenza.
     */
    private final GestioneNoleggioService noleggioService;

    /**
     * Endpoint per aggiungere una valutazione di un utente.
     *
     * @param valutazioneDTO Dati della valutazione dell'utente.
     * @return ResponseEntity contenente l'esito dell'operazione.
     */
    @PostMapping("/aggiungi-valutazione-utente")
    public ResponseEntity<String> aggiungiValutazioneUtente(@RequestBody ValutazioneDTO valutazioneDTO) {

        ResponseDTO message = new ResponseDTO();
        message.message = "Errore durante l'inserimento dei dati";

        HashMap<String, String> tester = new HashMap<>();
        tester.put(valutazioneDTO.getDescrizione(), "^[\\sa-zA-Z0-9.,:;'-?!]{1,255}$");

        RegexTester regexTester = new RegexTester();
        if (!regexTester.toTest(tester)) {
            return responseService.InternalError(message);
        }

        ValutazioneUtente valutazione = new ValutazioneUtente();
        Utente utente = areaPersonaleService.getDatiPrivati(valutazioneDTO.getValutatore());
        valutazione.setValutatore(utente);
        valutazione.setDescrizione(valutazioneDTO.getDescrizione());
        valutazione.setValutato(areaPersonaleService.getDatiPrivati(valutazioneDTO.getValutato()));
        valutazione.setNoleggio(noleggioService.getNoleggio(valutazioneDTO.getNoleggio()));
        if(valutazioneDTO.getVoto() >= 0 && valutazioneDTO.getVoto() <= 10){
            valutazione.setVoto(valutazioneDTO.getVoto());
        } else{
            return responseService.InternalError();
        }

        if(valutazione.getValutato()!= null && valutazione.getValutatore()!= null && valutazione.getNoleggio() != null){
            valutazione = valutazioneService.addValutazioneUtente(valutazione);
            ValutazioneDTO item = new ValutazioneDTO().convertFromValutazioneUtente(valutazione);
            return responseService.Ok(item);
        }
        else
            return responseService.InternalError();
    }

    /**
     * Endpoint per visualizzare le valutazioni di un utente.
     *
     * @param valutato Identificativo dell'utente valutato.
     * @return ResponseEntity contenente la lista delle valutazioni dell'utente nel formato JSON.
     */
    @GetMapping("/visualizza-valutazioni-utente")
    public ResponseEntity<String> visualizzaValutazioniUtente(@RequestParam long valutato){

        Utente utente = areaPersonaleService.getDatiPrivati(valutato);
        if(utente!= null){
            List<ValutazioneUtente> valutazioni = valutazioneService.findAllByUtente(utente);
            List<ValutazioneDTO> list = new ArrayList<>();
            for (ValutazioneUtente vu: valutazioni) {
                ValutazioneDTO item = new ValutazioneDTO().convertFromValutazioneUtente(vu);
                list.add(item);
            }
            return responseService.Ok(list);
        }
        else
            return responseService.InternalError();
    }

    /**
     * Endpoint per visualizzare la media delle valutazioni di un utente.
     *
     * @param valutato Identificativo dell'utente valutato.
     * @return ResponseEntity contenente la media delle valutazioni dell'utente nel formato JSON.
     */
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

    /**
     * Endpoint per aggiungere una valutazione di un annuncio.
     *
     * @param valutazioneDTO Dati della valutazione dell'annuncio.
     * @return ResponseEntity contenente l'esito dell'operazione.
     */
    @PostMapping("/aggiungi-valutazione-oggetto")
    public ResponseEntity<String> aggiungiValutazioneOggetto(@RequestBody ValutazioneDTO valutazioneDTO) {

        ResponseDTO message = new ResponseDTO();
        message.message = "Errore durante l'inserimento dei dati";

        HashMap<String, String> tester = new HashMap<>();
        tester.put(valutazioneDTO.getDescrizione(), "^[\\sa-zA-Z0-9.,:;'-?!]{1,255}$");

        RegexTester regexTester = new RegexTester();
        if (!regexTester.toTest(tester)) {
            return responseService.InternalError(message);
        }

        ValutazioneOggetto valutazione = new ValutazioneOggetto();
        Utente utente = areaPersonaleService.getDatiPrivati(valutazioneDTO.getValutatore());
        valutazione.setValutatore(utente);
        valutazione.setDescrizione(valutazioneDTO.getDescrizione());
        valutazione.setAnnuncio(annuncioService.getAnnuncio(valutazioneDTO.getValutato()).orElse(null));
        valutazione.setNoleggio(noleggioService.getNoleggio(valutazioneDTO.getNoleggio()));
        if(valutazioneDTO.getVoto() >= 0 && valutazioneDTO.getVoto() <= 10){
            valutazione.setVoto(valutazioneDTO.getVoto());
        } else{
            return responseService.InternalError();
        }

        if(valutazione.getAnnuncio()!= null && valutazione.getValutatore()!= null && valutazione.getNoleggio() != null){
            valutazione = valutazioneService.addValutazioneOggetto(valutazione);
            return responseService.Ok(valutazione);
        }
        else
            return responseService.InternalError();
    }

    /**
     * Endpoint per visualizzare le valutazioni di un annuncio.
     *
     * @param id Identificativo dell'annuncio.
     * @return ResponseEntity contenente la lista delle valutazioni dell'annuncio nel formato JSON.
     */
    @GetMapping("/visualizza-valutazioni-annuncio")
    public ResponseEntity<String> visualizzaValutazioniAnnuncio(@RequestParam long id){

        Annuncio annuncio = annuncioService.getAnnuncio(id).orElse(null);

        List<ValutazioneDTO> list = new ArrayList<>();

        if(annuncio!= null){
            List<ValutazioneOggetto> valutazioni = valutazioneService.findAllByAnnuncio(annuncio);
            list = new ArrayList<>();
            for (ValutazioneOggetto vo: valutazioni) {
                ValutazioneDTO item = new ValutazioneDTO().convertFromValutazioneOggetto(vo);
                list.add(item);
            }
        }
        return responseService.Ok(list);
    }

    /**
     * Endpoint per visualizzare la media delle valutazioni di un annuncio.
     *
     * @param id Identificativo dell'annuncio.
     * @return ResponseEntity contenente la media delle valutazioni dell'annuncio nel formato JSON.
     */
    @GetMapping("/visualizza-media-valutazioni-annuncio")
    public ResponseEntity<String> visualizzaMediaValutazioniOggetto(@RequestParam long id){

        Annuncio annuncio = annuncioService.getAnnuncio(id).orElse(null);
        if(annuncio!= null){
            double media = valutazioneService.mediaValutazioniOggettoByAnnuncio(annuncio);
            return responseService.Ok(media);
        }
        else
            return responseService.InternalError();
    }
}
