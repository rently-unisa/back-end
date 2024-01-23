package it.unisa.c02.rently.rently_application.business.gestioneAutenticazione.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.unisa.c02.rently.rently_application.business.gestioneAutenticazione.service.GestioneAutenticazioneService;
import it.unisa.c02.rently.rently_application.commons.psw.PswCoder;
import it.unisa.c02.rently.rently_application.commons.services.regexService.RegexTester;
import it.unisa.c02.rently.rently_application.commons.services.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.data.dto.ResponseDTO;
import it.unisa.c02.rently.rently_application.data.dto.UtenteDTO;
import it.unisa.c02.rently.rently_application.data.dto.UtenteLoginDTO;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import it.unisa.c02.rently.rently_application.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


/**
 * Questa classe gestisce le richieste relative all'autenticazione degli utenti attraverso i servizi forniti da GestioneAutenticazioneService.
 * Fornisce endpoint RESTful per aggiungere e accedere agli Utenti.
 * Le risposte vengono restituite nel formato JSON attraverso ResponseEntity<String>, utilizzando le funzionalità
 * di ResponseService per gestire la costruzione delle risposte standardizzate.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/autenticazione")
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
public class GestioneAutenticazioneController {

    /**
     * Service per la gestione delle risposte alle richieste.
     */
    @Autowired
    ResponseService responseService;

    /**
     * Service per effettuare le operazioni di persistenza.
     */
    private final GestioneAutenticazioneService autenticazioneService;

    /**
     * Restituisce l'utente associato all'indirizzo email e alla password specificati nell'UtenteDTO passato come parametro.
     *
     * @param data UtenteDTO contenente email e password dell'utente.
     * @return ResponseEntity contenente l'Utente associato all'indirizzo email e alla password o un messaggio di errore in formato JSON.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UtenteLoginDTO data) {

        ResponseDTO response = new ResponseDTO();
        response.message = "Login fallito";

        try {
            String oPassword = new PswCoder().codificaPassword(data.getPassword());
            Utente utente = autenticazioneService.login(data.getEmail(), oPassword);
            if (utente != null) {

                UtenteDTO item = new UtenteDTO().convertFromModel(utente);

                ObjectNode userNode = new ObjectMapper().convertValue(item, ObjectNode.class);
                userNode.remove("password");
                Map claimMap = new HashMap<>(0);
                claimMap.put("user", userNode);
                item.setToken(JwtProvider.createJwt(item.getEmail(), claimMap));

                return responseService.Ok(item);
            } else {
                return responseService.InternalError(response);
            }
        } catch (Exception ex)
        {
            return responseService.InternalError(response);
        }
    }


    /**
     * Aggiunge un nuovo Utente sulla piattaforma se non ne esiste un altro con lo stesso username e la stessa email.
     *
     * @param data UtenteDTO contenente i dati dell'Utente da aggiungere alla piattaforma.
     * @return ResponseEntity contenente l'Utente registrato o un messaggio di errore in formato JSON.
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UtenteDTO data) throws NoSuchAlgorithmException {

        ResponseDTO response = new ResponseDTO();
        response.message = "";


        HashMap<String, String> tester = new HashMap<>();
        tester.put(data.getEmail(), "^[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{1,100}$");
        tester.put(data.getUsername(), "^[a-zA-Z0-9.,'-_]{5,100}$");
        tester.put(data.getNome(), "^[\\sa-zA-Z0-9.,']{1,100}$");
        tester.put(data.getCognome(), "^[\\sa-zA-Z0-9.,']{1,100}$");
        tester.put(data.getPassword(),"^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");

        RegexTester regexTester = new RegexTester();
        if (!regexTester.toTest(tester)) {
            response.message = "I dati inseriti non sono validi";
            return responseService.InternalError(response);
        }

        try {
            Utente utente = new Utente();
            utente.setUsername(data.getUsername());
            utente.setEmail(data.getEmail());
            utente.setPassword( new PswCoder().codificaPassword(data.getPassword()));
            utente.setNome(data.getNome());
            utente.setCognome(data.getCognome());

            if(autenticazioneService.checkUsername(utente.getUsername()))
            {
                response.message = "Username già esistente!";
                return responseService.InternalError(response);
            }

            if(autenticazioneService.checkEmail(utente.getEmail()))
            {
                response.message = "Email già esistente!";
                return responseService.InternalError(response);
            }

            autenticazioneService.signUp(utente);

            if(utente.getUsername().equals("") || utente.getPassword().equals(""))
            {
                response.message = "Utente non registrato";
                return responseService.InternalError(response);
            }

            UtenteDTO item = new UtenteDTO().convertFromModel(utente);

            ObjectNode userNode = new ObjectMapper().convertValue(item, ObjectNode.class);
            userNode.remove("password");
            Map claimMap = new HashMap<>(0);
            claimMap.put("user", userNode);
            item.setToken(JwtProvider.createJwt(item.getEmail(), claimMap));

            return responseService.Ok(item);
        } catch (Exception ex)
        {
            return responseService.InternalError(response);
        }

    }
}
