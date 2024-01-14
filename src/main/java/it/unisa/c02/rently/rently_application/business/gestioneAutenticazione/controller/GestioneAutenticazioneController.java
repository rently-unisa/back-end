package it.unisa.c02.rently.rently_application.business.gestioneAutenticazione.controller;

import it.unisa.c02.rently.rently_application.business.gestioneAutenticazione.service.GestioneAutenticazioneService;
import it.unisa.c02.rently.rently_application.commons.psw.PswCoder;
import it.unisa.c02.rently.rently_application.commons.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.data.dto.ResponseDTO;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import it.unisa.c02.rently.rently_application.data.DTO.UtenteDTO;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/autenticazione")
// HelloController.java
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

    @Autowired
    ResponseService responseService;

    private final GestioneAutenticazioneService autenticazioneService;

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {

        ResponseDTO response = new ResponseDTO();
        response.message = "Login fallito";

        try {
            String oPassword = new PswCoder().codificaPassword(password);
            Utente utente = autenticazioneService.login(email, oPassword);
            if (utente != null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(utente, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return responseService.Ok(utente);
            } else {
                return responseService.InternalError(utente);
            }
        } catch (Exception ex)
        {
            return responseService.InternalError(response);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UtenteDTO data) throws NoSuchAlgorithmException {

        ResponseDTO response = new ResponseDTO();
        response.message = "";

        try {
            Utente item = new Utente();
            item.setUsername(data.getUsername());
            item.setEmail(data.getEmail());
            item.setPassword( new PswCoder().codificaPassword(data.getPassword()));
            item.setNome(data.getNome());
            item.setCognome(data.getCognome());
            autenticazioneService.signUp(item);

            if(item.getUsername().equals("") || item.getPassword().equals(""))
            {
                response.message = "Utente non registrato";
                return responseService.InternalError(response);
            }

            return responseService.Ok(item);
        } catch (Exception ex)
        {
            return responseService.InternalError(response);
        }

    }
}
