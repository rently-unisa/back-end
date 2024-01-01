package it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.controller;
import it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service.GestioneAnnuncioService;
import it.unisa.c02.rently.rently_application.data.dto.AnnuncioDTO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gestione-annuncio")
public class GestioneAnnuncioController {

    private final GestioneAnnuncioService gestioneAnnuncioService;
    @PostMapping("/add")
    public Annuncio addAnnuncio(@RequestBody AnnuncioDTO data) {
        Annuncio item = new Annuncio();

        item.setNome(data.getNome());
        item.setStrada(data.getStrada());
        item.setCitta(data.getCitta());
        item.setCAP(data.getCAP());
        item.setDescrizione(data.getDescrizione());
        item.setPrezzo(data.getPrezzo());
        item.setImmagine(data.getImmagine());
        item.setCategoria(Annuncio.EnumCategoria.valueOf(data.getCategoria()));
        item.setCondizione(Annuncio.EnumCondizione.valueOf(data.getCondizione()));
        item.setDataFine(data.getDataFine());

        // Aggiungere gestione utente con gestioneUtenteService

        return gestioneAnnuncioService.addAnnuncio(item);
    }
}
