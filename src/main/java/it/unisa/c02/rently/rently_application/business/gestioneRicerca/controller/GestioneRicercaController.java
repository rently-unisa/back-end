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

    @GetMapping("/categoria")
    public List<Annuncio> searchByCategoria(@RequestParam String categoria) {
        return ricercaService.searchByCategoria(categoria);
    }

    @GetMapping("/condizione")
    public List<Annuncio> searchByCondizione(@RequestParam String condizione) {
        return ricercaService.searchByCondizione(condizione);
    }

    @GetMapping("/data")
    public List<Annuncio> searchByData(@RequestParam Date inizio, @RequestParam Date fine) {
        return ricercaService.searchByData(inizio, fine);
    }

    @GetMapping("/descrizione")
    public List<Annuncio> searchByDescrizione(@RequestParam String descrizione) {
        return ricercaService.searchByDescrizione(descrizione);
    }

    @GetMapping("/all")
    public List<Annuncio> searchAll() {
        return ricercaService.searchAll();
    }

    @GetMapping("/premium")
    public ResponseEntity<String> searchAnnunciPremium() {
        List<Annuncio> annunci =  ricercaService.searchAnnunciPremium();
        List<AnnuncioDTO> list = new ArrayList<AnnuncioDTO>();
        for (Annuncio a: annunci) {
            AnnuncioDTO item = new AnnuncioDTO().convertFromModel(a);
            list.add(item);
        }
        return responseService.Ok(list);
    }
}

