package it.unisa.c02.rently.rently_application.business.gestioneNoleggio.controller;

import it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service.GestioneAnnuncioService;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.business.gestioneNoleggio.service.GestioneNoleggioService;
import it.unisa.c02.rently.rently_application.business.gestioneValutazione.service.GestioneValutazioneService;
import it.unisa.c02.rently.rently_application.commons.services.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.data.dto.NoleggioDTO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe gestisce le richieste relative ai noleggi attraverso i servizi forniti da GestioneAreaPersonaleService,
 * GestioneAnnuncioService, GestioneNoleggioService, GestioneValutazioneService e ResponseService.
 * Fornisce endpoint RESTful per accedere e aggiungere noleggi sulla piattaforma.
 * Le risposte vengono restituite nel formato JSON attraverso ResponseEntity<String>, utilizzando le funzionalità
 * di ResponseService per gestire la costruzione delle risposte standardizzate.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/noleggio")
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
public class GestioneNoleggioController {

    /**
     * Service per effettuare le operazioni di persistenza.
     */
    private final GestioneNoleggioService noleggioService;

    /**
     * Service per la gestione delle risposte alle richieste.
     */
    private final ResponseService responseService;

    /**
     * Service per effettuare le operazioni di persistenza.
     */
    private final GestioneAreaPersonaleService areaPersonaleService;

    /**
     * Service per effettuare le operazioni di persistenza.
     */
    private final GestioneAnnuncioService annuncioService;

    /**
     * Service per effettuare le operazioni di persistenza.
     */
    private final GestioneValutazioneService valutazioneService;


    /**
     * Restituisce tutti i noleggi, eccetto le richieste, effettuati da un noleggiante passato come parametro.
     *
     * @param idUtente ID del noleggiante di cui si vogliono ricevere i noleggi.
     * @return ResponseEntity contenente la lista dei noleggi del noleggiante o un messaggio di errore in formato JSON.
     */
    @GetMapping("/noleggiante")
    public ResponseEntity<String> getNoleggiByNoleggiante(@RequestParam long idUtente) {

        Utente noleggiante = areaPersonaleService.getDatiPrivati(idUtente);

        if(noleggiante!=null){
            List<Noleggio> noleggi = noleggioService.getNoleggiByNoleggiante(noleggiante);
            List<NoleggioDTO> list = new ArrayList<>();
            for (Noleggio n: noleggi) {
                NoleggioDTO item = new NoleggioDTO().convertFromModel(n);
                item.setValutazioneAlNoleggiatore(valutazioneService.valutazioneNoleggiatoreIsPresent(n));
                item.setValutazioneAnnuncio(valutazioneService.valutazioneAnnuncioIsPresent(n));
                item.setValutazioneAlNoleggiante(valutazioneService.valutazioneNoleggianteIsPresent(n));
                if(item.isValutazioneAlNoleggiante() && item.isValutazioneAlNoleggiatore() && item.isValutazioneAnnuncio()){

                    item.setStato("CONCLUSOCONVALUTAZIONE");
                }

                list.add(item);
            }
            return responseService.Ok(list);
        }
        else
            return responseService.InternalError();
    }

    /**
     * Restituisce tutti i noleggi, eccetto le richieste, effettuati da un noleggiatore passato come parametro.
     *
     * @param idUtente ID del noleggiatore di cui si vogliono ricevere i noleggi.
     * @return ResponseEntity contenente la lista dei noleggi del noleggiatore o un messaggio di errore in formato JSON.
     */
    @GetMapping("/noleggiatore")
    public ResponseEntity<String> getNoleggiByNoleggiatore(@RequestParam long idUtente) {

        Utente noleggiatore = areaPersonaleService.getDatiPrivati(idUtente);

        if(noleggiatore!=null){
            List<Noleggio> noleggi = noleggioService.getNoleggiByNoleggiatore(noleggiatore);
            List<NoleggioDTO> list = new ArrayList<>();
            for (Noleggio n: noleggi) {
                NoleggioDTO item = new NoleggioDTO().convertFromModel(n);
                item.setValutazioneAlNoleggiatore(valutazioneService.valutazioneNoleggiatoreIsPresent(n));
                item.setValutazioneAnnuncio(valutazioneService.valutazioneAnnuncioIsPresent(n));
                item.setValutazioneAlNoleggiante(valutazioneService.valutazioneNoleggianteIsPresent(n));
                if(item.isValutazioneAlNoleggiante() && item.isValutazioneAlNoleggiatore() && item.isValutazioneAnnuncio()){

                    item.setStato("CONCLUSOCONVALUTAZIONE");
                }
                list.add(item);
            }
            return responseService.Ok(list);
        }
        else
            return responseService.InternalError();
    }

    /**
     * Restituisce tutte le richieste, anche in stato RIFIUTATA e ACCETTATA, effettuate da un noleggiante passato come parametro.
     *
     * @param idUtente ID del noleggiante di cui si vogliono ricevere le richieste.
     * @return ResponseEntity contenente la lista delle richieste del noleggiante o un messaggio di errore in formato JSON.
     */
    @GetMapping("/richieste/noleggiante")
    public ResponseEntity<String> getRichiesteByNoleggiante(@RequestParam long idUtente) {

        Utente noleggiante = areaPersonaleService.getDatiPrivati(idUtente);

        if(noleggiante!=null){
            List<Noleggio> noleggi = noleggioService.getRichiesteByNoleggiante(noleggiante);
            List<NoleggioDTO> list = new ArrayList<>();
            for (Noleggio n: noleggi) {
                NoleggioDTO item = new NoleggioDTO().convertFromModel(n);
                list.add(item);
            }
            return responseService.Ok(list);
        }
        else
            return responseService.InternalError();
    }

    /**
     * Restituisce tutte le richieste, anche in stato RIFIUTATA e ACCETTATA, effettuate da un noleggiatore passato come parametro.
     *
     * @param idUtente ID del noleggiatore di cui si vogliono ricevere le richieste.
     * @return ResponseEntity contenente la lista delle richieste del noleggiatore o un messaggio di errore in formato JSON.
     */
    @GetMapping("/richieste/noleggiatore")
    public ResponseEntity<String> getRichiesteByNoleggiatore(@RequestParam long idUtente) {

        Utente noleggiatore = areaPersonaleService.getDatiPrivati(idUtente);

        if(noleggiatore!=null){
            List<Noleggio> noleggi = noleggioService.getRichiesteByNoleggiatore(noleggiatore);
            List<NoleggioDTO> list = new ArrayList<>();
            for (Noleggio n: noleggi) {
                NoleggioDTO item = new NoleggioDTO().convertFromModel(n);
                list.add(item);
            }
            return responseService.Ok(list);
        }
        else
            return responseService.InternalError();
    }

    /**
     * Restituisce le informazioni di un noleggio specifico in base all'identificativo.
     *
     * @param idNoleggio Identificativo del noleggio da visualizzare.
     * @return ResponseEntity contenente le informazioni del noleggio o un messaggio di errore in formato JSON.
     */
    @GetMapping("/visualizza-noleggio")
    public ResponseEntity<String> getNoleggio(@RequestParam long idNoleggio) {

        try {
            Noleggio noleggio = noleggioService.getNoleggio(idNoleggio);
            NoleggioDTO item = new NoleggioDTO().convertFromModel(noleggio);

            return responseService.Ok(item);

        } catch (Exception ex)
        {
            return responseService.InternalError();
        }
    }

    /**
     * Aggiunge un nuovo noleggio alla piattaforma.
     *
     * @param data NoleggioDTO contenente le informazioni del noleggio.
     * @return ResponseEntity contenente le informazioni del noleggio aggiunto o un messaggio di errore in formato JSON.
     */
    @PostMapping("/aggiungi-noleggio")
    public ResponseEntity<String> aggiungiNoleggio(@RequestBody NoleggioDTO data){

        try {

            Annuncio annuncio = annuncioService.getAnnuncio(data.getAnnuncio()).orElse(null);

            List<Noleggio> list = noleggioService.checkDisponibilita(annuncio,
                    Date.valueOf(data.getDataInizio()),
                    Date.valueOf(data.getDataFine()));

            if(Date.valueOf(data.getDataFine()).after(annuncio.getDataFine()))
            {
                return responseService.InternalError();
            }

            if (list.isEmpty()) {
                Noleggio item = new Noleggio();
                item.setStato(Noleggio.EnumStato.RICHIESTA);
                item.setPrezzoTotale(data.getPrezzoTotale());
                item.setDataInizio(Date.valueOf(data.getDataInizio()));
                item.setDataFine(Date.valueOf(data.getDataFine()));
                item.setDataRichiesta(Date.valueOf(data.getDataRichiesta()));
                item.setNoleggiante(areaPersonaleService.getDatiPrivati(data.getNoleggiante()));
                item.setNoleggiatore(areaPersonaleService.getDatiPrivati(data.getNoleggiatore()));
                item.setAnnuncio(annuncioService.getAnnuncio(data.getAnnuncio()).orElse(null));

                if (item.getNoleggiante() != null && item.getNoleggiatore() != null && item.getAnnuncio() != null) {
                    item = noleggioService.addNoleggio(item);

                    NoleggioDTO noleggioDto = new NoleggioDTO().convertFromModel(item);

                    return responseService.Ok(noleggioDto);
                } else
                    return responseService.InternalError();
            } else
                return responseService.InternalError();
        }
        catch (Exception ex) {
            return responseService.InternalError();
        }
    }


    /**
     * Modofica un noleggio presente sulla piattaforma.
     *
     * @param data NoleggioDTO contenente le informazioni del noleggio modificate.
     * @return ResponseEntity contenente le informazioni del noleggio modificato o un messaggio di errore in formato JSON.
     */
    @PostMapping("/salva-noleggio")
    public ResponseEntity<String> salvaNoleggio(@RequestBody NoleggioDTO data){

        Noleggio item = noleggioService.getNoleggio(data.getId());
        item.setStato(Noleggio.EnumStato.valueOf(data.getStato()));
        item.setPrezzoTotale(data.getPrezzoTotale());
        item.setDataInizio(Date.valueOf(data.getDataInizio()));
        item.setDataFine(Date.valueOf(data.getDataFine()));
        item.setDataRichiesta(Date.valueOf(data.getDataRichiesta()));
        item.setNoleggiante(areaPersonaleService.getDatiPrivati(data.getNoleggiante()));
        item.setNoleggiatore(areaPersonaleService.getDatiPrivati(data.getNoleggiatore()));
        item.setAnnuncio(annuncioService.getAnnuncio(data.getAnnuncio()).orElse(null));

        if(item.getNoleggiante() != null && item.getNoleggiatore()!= null && item.getAnnuncio() != null){
            item = noleggioService.updateStatoNoleggio(item);

            NoleggioDTO noleggioDTO = new NoleggioDTO().convertFromModel(item);
            return responseService.Ok(noleggioDTO);
        }
        else
            return responseService.InternalError();

    }
}
