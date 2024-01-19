package it.unisa.c02.rently.rently_application.business.gestioneNoleggio.controller;

import it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service.GestioneAnnuncioService;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.business.gestioneNoleggio.service.GestioneNoleggioService;
import it.unisa.c02.rently.rently_application.business.gestioneValutazione.service.GestioneValutazioneService;
import it.unisa.c02.rently.rently_application.commons.services.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.data.dto.NoleggioDTO;
import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    private final GestioneNoleggioService noleggioService;
    private final ResponseService responseService;
    private final GestioneAreaPersonaleService areaPersonaleService;
    private final GestioneAnnuncioService annuncioService;
    private final GestioneValutazioneService valutazioneService;

    @GetMapping("/noleggiante")
    public ResponseEntity<String> getNoleggiByNoleggiante(@RequestParam long idUtente) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Utente noleggiante = (Utente) authentication.getPrincipal();

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

    @GetMapping("/noleggiatore")
    public ResponseEntity<String> getNoleggiByNoleggiatore(@RequestParam long idUtente) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Utente noleggiatore = (Utente) authentication.getPrincipal();

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

    @GetMapping("/richieste/noleggiante")
    public ResponseEntity<String> getRichiesteByNoleggiante(@RequestParam long idUtente) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       //Utente noleggiante = (Utente) authentication.getPrincipal();

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

    @GetMapping("/richieste/noleggiatore")
    public ResponseEntity<String> getRichiesteByNoleggiatore(@RequestParam long idUtente) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Utente noleggiatore = (Utente) authentication.getPrincipal();

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
    @PostMapping("/aggiungi-noleggio")
    public ResponseEntity<String> aggiungiNoleggio(@RequestBody NoleggioDTO data){

        List<Noleggio> list = noleggioService.checkDisponibilita(data.getAnnuncio(), data.getDataInizio(), data.getDataFine());

        if(list == null) {

            Noleggio item = new Noleggio();
            item.setStato(Noleggio.EnumStato.valueOf(data.getStato()));
            item.setPrezzoTotale(data.getPrezzoTotale());
            item.setDataInizio(data.getDataInizio());
            item.setDataFine(data.getDataFine());
            item.setDataRichiesta(data.getDataRichiesta());
            item.setNoleggiante(areaPersonaleService.getDatiPrivati(data.getNoleggiante()));
            item.setNoleggiatore(areaPersonaleService.getDatiPrivati(data.getNoleggiatore()));
            item.setAnnuncio(annuncioService.getAnnuncio(data.getAnnuncio()).orElse(null));

            if (item.getNoleggiante() != null && item.getNoleggiatore() != null && item.getAnnuncio() != null) {
                item = noleggioService.addNoleggio(item);
                return responseService.Ok(item);
            } else
                return responseService.InternalError();
        }else
            return responseService.InternalError();
    }
    @PostMapping("/salva-noleggio")
    public ResponseEntity<String> salvaNoleggio(@RequestBody NoleggioDTO data){

        Noleggio item = new Noleggio();
        item.setStato(Noleggio.EnumStato.valueOf(data.getStato()));
        item.setPrezzoTotale(data.getPrezzoTotale());
        item.setDataInizio(data.getDataInizio());
        item.setDataFine(data.getDataFine());
        item.setDataRichiesta(data.getDataRichiesta());
        item.setNoleggiante(areaPersonaleService.getDatiPrivati(data.getNoleggiante()));
        item.setNoleggiatore(areaPersonaleService.getDatiPrivati(data.getNoleggiatore()));
        item.setAnnuncio(annuncioService.getAnnuncio(data.getAnnuncio()).orElse(null));

        if(item.getNoleggiante() != null && item.getNoleggiatore()!= null && item.getAnnuncio() != null){
            item = noleggioService.updateStatoNoleggio(item);
            return responseService.Ok(item);
        }
        else
            return responseService.InternalError();

    }
}
