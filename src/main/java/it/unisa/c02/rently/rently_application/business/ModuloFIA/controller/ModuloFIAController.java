package it.unisa.c02.rently.rently_application.business.ModuloFIA.controller;

import it.unisa.c02.rently.rently_application.business.ModuloFIA.service.ModuloFIAService;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.business.gestioneRicerca.service.GestioneRicercaService;
import it.unisa.c02.rently.rently_application.commons.services.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.data.dto.AnnuncioDTO;
import it.unisa.c02.rently.rently_application.data.dto.UtenteFiaDTO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/FIA")
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
public class ModuloFIAController {

    private final GestioneRicercaService ricercaService;

    private final ResponseService responseService;

    private final HttpServletRequest httpServletRequest;

    private final ModuloFIAService moduloFIAService;

    private final GestioneAreaPersonaleService areaPersonaleservice;

    @PostMapping("/predict/{idUtente}")
    public ResponseEntity<String> predizioneCategoria(@RequestBody UtenteFiaDTO utenteFiaDTO, @PathVariable("idUtente") long idUtente){

        Utente utente=  areaPersonaleservice.getDatiPrivati(idUtente);

        boolean predizione = moduloFIAService.RichiestaFlask(utenteFiaDTO,utente);
        if(predizione){

            return responseService.Ok();
        }
        else
            return responseService.InternalError();

    }
    @GetMapping("/annunciCategoria")
    public ResponseEntity<String> searchAnnunciByCategoria(@RequestParam long idUtente) {

        Utente utente= areaPersonaleservice.getDatiPrivati(idUtente);
        String categoria = moduloFIAService.getCategoria(utente);
        if(utente != null && categoria != null) {
            List<Annuncio> annunci = ricercaService.searchByCategoria(Annuncio.EnumCategoria.valueOf(categoria));
            List<AnnuncioDTO> list = new ArrayList<AnnuncioDTO>();

            String serverAddress = String.format(
                    "%s://%s:%d",
                    httpServletRequest.getScheme(),
                    httpServletRequest.getServerName(),
                    httpServletRequest.getServerPort());

            for (Annuncio a : annunci) {
                AnnuncioDTO item = new AnnuncioDTO().convertFromModel(a);
                item.setServerImage(a, serverAddress);
                list.add(item);
            }

            return responseService.Ok(list);
        }
        else
            return responseService.InternalError();
    }
}

