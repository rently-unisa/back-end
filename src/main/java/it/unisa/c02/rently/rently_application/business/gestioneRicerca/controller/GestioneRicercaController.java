package it.unisa.c02.rently.rently_application.business.gestioneRicerca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import it.unisa.c02.rently.rently_application.business.gestioneRicerca.service.GestioneRicercaService;
import it.unisa.c02.rently.rently_application.commons.jsonHelper.JsonHelper;
import it.unisa.c02.rently.rently_application.commons.services.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.commons.services.storageService.FilesStorageService;
import it.unisa.c02.rently.rently_application.data.dto.AnnuncioDTO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ricerca")
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
public class GestioneRicercaController {

    private final GestioneRicercaService ricercaService;
    private final ResponseService responseService;
    private final HttpServletRequest httpServletRequest;

    @GetMapping("/categoria")
    public ResponseEntity<String>  searchByCategoria(@RequestParam String categoria) {
        List<Annuncio> annunci = ricercaService.searchByCategoria(categoria);
        List<AnnuncioDTO> list = new ArrayList<AnnuncioDTO>();
        for (Annuncio a: annunci) {
            AnnuncioDTO item = new AnnuncioDTO().convertFromModel(a);
            list.add(item);
        }
        return responseService.Ok(list);
    }

    @GetMapping("/condizione")
    public ResponseEntity<String> searchByCondizione(@RequestParam String condizione) {
        List<Annuncio> annunci = ricercaService.searchByCondizione(condizione);
        List<AnnuncioDTO> list = new ArrayList<AnnuncioDTO>();
        for (Annuncio a: annunci) {
            AnnuncioDTO item = new AnnuncioDTO().convertFromModel(a);
            list.add(item);
        }
        return responseService.Ok(list);
    }

    @GetMapping("/data")
    public ResponseEntity<String> searchByData(@RequestParam Date inizio, @RequestParam Date fine) {
        List<Annuncio> annunci = ricercaService.searchByData(inizio, fine);
        List<AnnuncioDTO> list = new ArrayList<AnnuncioDTO>();
        for (Annuncio a: annunci) {
            AnnuncioDTO item = new AnnuncioDTO().convertFromModel(a);
            list.add(item);
        }
        return responseService.Ok(list);
    }

    @GetMapping("/descrizione")
    public ResponseEntity<String> searchByDescrizione(@RequestParam String descrizione) {
        List<Annuncio> annunci =  ricercaService.searchByDescrizione(descrizione);
        List<AnnuncioDTO> list = new ArrayList<AnnuncioDTO>();
        for (Annuncio a: annunci) {
            AnnuncioDTO item = new AnnuncioDTO().convertFromModel(a);
            list.add(item);
        }
        return responseService.Ok(list);
    }

    @GetMapping("/all")
    public ResponseEntity<String> searchAll() {
        List<Annuncio> annunci =  ricercaService.searchAll();
        List<AnnuncioDTO> list = new ArrayList<AnnuncioDTO>();
        for (Annuncio a: annunci) {
            AnnuncioDTO item = new AnnuncioDTO().convertFromModel(a);
            list.add(item);
        }
        return responseService.Ok(list);
    }

    @GetMapping("/premium")
    public ResponseEntity<String> searchAnnunciPremium() {
        List<Annuncio> annunci =  ricercaService.searchAnnunciPremium();
        List<AnnuncioDTO> list = new ArrayList<AnnuncioDTO>();

        String serverAddress = String.format(
                "%s://%s:%d",
                httpServletRequest.getScheme(),
                httpServletRequest.getServerName(),
                httpServletRequest.getServerPort());

        for (Annuncio a: annunci) {
            AnnuncioDTO item = new AnnuncioDTO().convertFromModel(a);
            item.setServerImage(a, serverAddress);
            list.add(item);
        }
        return responseService.Ok(list);
    }
}

