package it.unisa.c02.rently.rently_application.business.gestioneAutenticazione.controller;

import it.unisa.c02.rently.rently_application.business.gestioneAutenticazione.service.GestioneAutenticazioneService;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/autenticazione")
public class GestioneAutenticazioneController {

    private final GestioneAutenticazioneService autenticazioneService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        Utente utente = autenticazioneService.login(email, password);
        if (utente != null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(utente, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("Login effettuato con successo", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Credenziali non valide", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody Utente utente) throws NoSuchAlgorithmException {
        autenticazioneService.signUp(utente);
    }
}
