package it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.controller;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.commons.psw.PswCoder;
import it.unisa.c02.rently.rently_application.data.DTO.UtenteDTO;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@SessionAttributes("loggedUser")
@RequestMapping("/gestione-area-personale")
public class GestioneAreaPersonaleController {

    private final GestioneAreaPersonaleService gestioneAreaPersonaleService;
    @GetMapping("/profilo-utente")
    public String profiloUtente(Model model) {

        Utente utente = (Utente) model.getAttribute("loggedUser");
        if(utente!= null){

            model.addAttribute("utente", utente);
            return "area-personale";
        }

        return "Ok"; // alla pagina di login
    }

    @PostMapping("/modifica-dati-utente")
    public String modifcaUtente(UtenteDTO utente, Model model, @RequestParam("nuovaPsw") String nuova, @RequestParam("confermaPsw")String conferma) throws NoSuchAlgorithmException {

        Utente daModificare = gestioneAreaPersonaleService.getDatiPrivati(utente.getId());

        if (!utente.getPassword().isEmpty() && !nuova.isEmpty() && !conferma.isEmpty()) {

                PswCoder coder = new PswCoder();
                String vecchiaPassword = coder.codificaPassword(utente.getPassword());

                if (daModificare.getPassword().equals(vecchiaPassword) && nuova.equals(conferma)) {
                    utente.setPassword(nuova);
                } else {
                    return "modifica-utente";
                }

        }

        Utente modificato= new Utente(utente.getId(),utente.getUsername(),utente.getNome(), utente.getCognome(),utente.getEmail(), utente.getPassword(), utente.isPremium());
        gestioneAreaPersonaleService.updateUtente(modificato);
        model.addAttribute("loggedUser", modificato);

        return "areaPersonale";
    }
}
