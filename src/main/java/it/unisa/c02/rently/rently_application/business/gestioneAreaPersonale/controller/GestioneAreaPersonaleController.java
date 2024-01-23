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

/**
 * Questa classe gestisce le richieste relative all'area personale degli utenti attraverso i servizi forniti da GestioneAreaPersonaleService.
 * Fornisce endpoint RESTful per accedere e modificare i dati personali degli utenti.
 * Le risposte vengono restituite nel formato JSON attraverso ResponseEntity<String>, utilizzando le funzionalità
 * di ResponseService per gestire la costruzione delle risposte standardizzate.
 */
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

    /**
     * Service per la gestione delle risposte alle richieste.
     */
    private final ResponseService responseService;

    /**
     * Service per effettuare le operazioni di persistenza.
     */
    private final GestioneAreaPersonaleService areaPersonaleService;

    /**
     * Ottiene il profilo dell'utente specificato dall'ID.
     *
     * @param id ID dell'utente di cui si vuole ottenere il profilo.
     * @return ResponseEntity contenente il profilo dell'utente in formato JSON.
     */
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

    /**
     * Modifica i dati dell'utente con le informazioni fornite nel corpo della richiesta.
     *
     * @param data UtenteDTO contenente i dati da modificare.
     * @return ResponseEntity contenente i risultati dell'operazione di modifica.
     */
    @PostMapping("/modifica-dati-utente")
    public ResponseEntity<String>  modificaUtente(@RequestBody UtenteDTO data) {

        ResponseDTO message = new ResponseDTO();
        message.message = "I parametri non rispettano le regex";

        HashMap<String, String> tester = new HashMap<>();
        tester.put(data.getEmail(), "^[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{1,100}$");
        tester.put(data.getUsername(), "^[a-zA-Z0-9.,'-_]{5,100}$");
        tester.put(data.getNome(), "^[\\sa-zA-Z0-9.,'èéòàùì]{1,100}$");
        tester.put(data.getCognome(), "^[\\sa-zA-Z0-9.,'èéòàùì]{1,100}$");

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
