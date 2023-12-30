package it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.controller;
import it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service.GestioneAnnuncioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GestioneAnnuncioController {

    private final GestioneAnnuncioService gestioneAnnuncioService;
    @GetMapping("/test")
    public String testMessage() {
        return "Ok";
    }
}
