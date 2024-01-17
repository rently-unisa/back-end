package it.unisa.c02.rently.rently_application.business.gestioneNoleggio.controller;

import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.business.gestioneNoleggio.service.GestioneNoleggioService;
import it.unisa.c02.rently.rently_application.commons.services.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.data.dto.NoleggioDTO;
import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/noleggio")
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

    @GetMapping("/noleggiante")
    public ResponseEntity<String> getNoleggiByNoleggiante(@RequestParam long idUtente) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Utente noleggiante = (Utente) authentication.getPrincipal();

        Utente noleggiante = areaPersonaleService.getDatiPrivati(idUtente);

        if(noleggiante!=null){
            List<Noleggio> noleggi = noleggioService.getNoleggiByNoleggiante(noleggiante);
            List<NoleggioDTO> list = new ArrayList<NoleggioDTO>();
            for (Noleggio n: noleggi) {
                NoleggioDTO item = new NoleggioDTO().convertFromModel(n);
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
            List<NoleggioDTO> list = new ArrayList<NoleggioDTO>();
            for (Noleggio n: noleggi) {
                NoleggioDTO item = new NoleggioDTO().convertFromModel(n);
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
            List<NoleggioDTO> list = new ArrayList<NoleggioDTO>();
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
            List<NoleggioDTO> list = new ArrayList<NoleggioDTO>();
            for (Noleggio n: noleggi) {
                NoleggioDTO item = new NoleggioDTO().convertFromModel(n);
                list.add(item);
            }
            return responseService.Ok(list);
        }
        else
            return responseService.InternalError();
    }

}
