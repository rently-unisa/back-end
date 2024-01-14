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
@RequestMapping("/api/area-personale")
public class GestioneAreaPersonaleController {

    private final GestioneAreaPersonaleService AreaPersonaleService;
    @GetMapping("/profilo-utente")
    public Utente profiloUtente() {

        Utente utente= new Utente(); //Prendi utente dalla sessione
        if(utente!= null){
            return AreaPersonaleService.getDatiPrivati(utente.getId());
        }
        return null;
    }

    @PostMapping("/modifica-dati-utente")
    public Utente modifcaUtente(UtenteDTO utente, @RequestParam("nuovaPsw") String nuova, @RequestParam("confermaPsw")String conferma) throws NoSuchAlgorithmException {

        Utente daModificare = AreaPersonaleService.getDatiPrivati(utente.getId()); //Prendi utente dalla sessione
        if (!utente.getPassword().isEmpty() && !nuova.isEmpty() && !conferma.isEmpty()) {

                PswCoder coder = new PswCoder();
                String vecchiaPassword = coder.codificaPassword(utente.getPassword());

                if (daModificare.getPassword().equals(vecchiaPassword) && nuova.equals(conferma)) {
                    utente.setPassword(nuova);
                } else
                    return null;
        }
        Utente modificato= new Utente(utente.getId(),utente.getUsername(),utente.getNome(), utente.getCognome(),utente.getEmail(), utente.getPassword(), utente.isPremium());

        return AreaPersonaleService.updateUtente(modificato);
    }
}
