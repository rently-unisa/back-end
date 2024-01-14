package it.unisa.c02.rently.rently_application.business.gestioneNoleggio.controller;

import it.unisa.c02.rently.rently_application.business.gestioneNoleggio.service.GestioneNoleggioService;
import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/noleggio")
public class GestioneNoleggioController {

    private final GestioneNoleggioService noleggioService;

    @GetMapping("/noleggiante")
    public ResponseEntity<List<Noleggio>> getNoleggiByNoleggiante() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utente noleggiante = (Utente) authentication.getPrincipal();

        List<Noleggio> noleggi = noleggioService.getNoleggiByNoleggiante(noleggiante);
        return ResponseEntity.ok(noleggi);
    }

    @GetMapping("/noleggiatore")
    public ResponseEntity<List<Noleggio>> getNoleggiByNoleggiatore() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utente noleggiatore = (Utente) authentication.getPrincipal();

        List<Noleggio> noleggi = noleggioService.getNoleggiByNoleggiatore(noleggiatore);
        return ResponseEntity.ok(noleggi);
    }

    @GetMapping("/richieste/noleggiante")
    public ResponseEntity<List<Noleggio>> getRichiesteByNoleggiante() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utente noleggiante = (Utente) authentication.getPrincipal();

        List<Noleggio> richieste = noleggioService.getRichiesteByNoleggiante(noleggiante);
        return ResponseEntity.ok(richieste);
    }

    @GetMapping("/richieste/noleggiatore")
    public ResponseEntity<List<Noleggio>> getRichiesteByNoleggiatore() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utente noleggiatore = (Utente) authentication.getPrincipal();

        List<Noleggio> richieste = noleggioService.getRichiesteByNoleggiatore(noleggiatore);
        return ResponseEntity.ok(richieste);
    }

}
