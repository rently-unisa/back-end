package it.unisa.c02.rently.rently_application.business.gestioneAnnuncio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GestioneAnnuncioController {
    @GetMapping("/test")
    public String testMessage() {
        return "Ok";
    }
}
