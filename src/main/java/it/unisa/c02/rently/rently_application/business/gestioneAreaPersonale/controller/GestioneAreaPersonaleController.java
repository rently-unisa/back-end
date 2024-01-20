package it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.controller;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.commons.psw.PswCoder;
import it.unisa.c02.rently.rently_application.commons.services.regexService.RegexTester;
import it.unisa.c02.rently.rently_application.commons.services.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.data.dto.ResponseDTO;
import it.unisa.c02.rently.rently_application.data.dto.UtenteDTO;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/area-personale")
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
public class GestioneAreaPersonaleController {

    private final ResponseService responseService;

    private final GestioneAreaPersonaleService areaPersonaleService;
    @GetMapping("/profilo-utente")
    public ResponseEntity<String> profiloUtente(@RequestParam("id") long id) {

        Utente utente = null;
        UtenteDTO item = new UtenteDTO();

        try {
            utente= areaPersonaleService.getDatiPrivati(id);

            if(utente == null){
                return responseService.InternalError(item);
            }
            else {
                item = new UtenteDTO().convertFromModel(utente);
            }
        } catch (Exception ex) {
            return responseService.InternalError();
        }

        return responseService.Ok(item);
    }

    @PostMapping("/modifica-dati-utente")
    public ResponseEntity<String>  modificaUtente(@RequestBody UtenteDTO data) {

        ResponseDTO message = new ResponseDTO();
        message.message = "I parametri non rispettano le regex";

        HashMap<String, String> tester = new HashMap<>();
        tester.put(data.getEmail(), "^[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{1,100}$");
        tester.put(data.getUsername(), "^[a-zA-Z0-9]{5,100}$");
        tester.put(data.getNome(), "^[a-zA-Z0-9]{1,100}$");
        tester.put(data.getCognome(), "^[a-zA-Z0-9]{1,100}$");

        RegexTester regexTester = new RegexTester();
        if (!regexTester.toTest(tester)) {
            return responseService.InternalError(message);
        }

        Utente item = areaPersonaleService.getDatiPrivati(data.getId());

        try {
            if (!data.getNuovaPassword().isEmpty() && !data.getConfermaNuovaPassword().isEmpty()) {
                PswCoder coder = new PswCoder();
                item.setPassword(coder.codificaPassword(data.getNuovaPassword()));
            }

            item.setNome(data.getNome());
            item.setCognome(data.getCognome());
            item.setEmail(data.getEmail());
            item.setPremium(data.isPremium());
            item.setUsername(data.getUsername());

            item = areaPersonaleService.updateUtente(item);


        } catch (Exception ex) {
            return responseService.InternalError();
        }
        return responseService.Ok(item);
    }
}
