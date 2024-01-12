package it.unisa.c02.rently.rently_application.business.gestioneRicerca.controller;

import it.unisa.c02.rently.rently_application.business.gestioneRicerca.service.GestioneRicercaService;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ricerca")
public class GestioneRicercaController {

    private final GestioneRicercaService ricercaService;

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
    public List<Annuncio> searchAnnunciPremium() {
        return ricercaService.searchAnnunciPremium();
    }
}

