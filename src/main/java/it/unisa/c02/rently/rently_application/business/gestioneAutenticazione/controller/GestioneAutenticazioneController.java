package it.unisa.c02.rently.rently_application.business.gestioneAutenticazione.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.unisa.c02.rently.rently_application.business.gestioneAutenticazione.service.GestioneAutenticazioneService;
import it.unisa.c02.rently.rently_application.commons.psw.PswCoder;
import it.unisa.c02.rently.rently_application.commons.services.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.data.dto.ResponseDTO;
import it.unisa.c02.rently.rently_application.data.dto.UtenteDTO;
import it.unisa.c02.rently.rently_application.data.dto.UtenteLoginDTO;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import it.unisa.c02.rently.rently_application.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    ResponseService responseService;

    private final GestioneAutenticazioneService autenticazioneService;

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

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UtenteDTO data) throws NoSuchAlgorithmException {

        ResponseDTO response = new ResponseDTO();
        response.message = "";

        try {
            Utente utente = new Utente();
            utente.setUsername(data.getUsername());
            utente.setEmail(data.getEmail());
            utente.setPassword( new PswCoder().codificaPassword(data.getPassword()));
            utente.setNome(data.getNome());
            utente.setCognome(data.getCognome());
            autenticazioneService.signUp(utente);

            if(utente.getUsername().equals("") || utente.getPassword().equals(""))
            {
                response.message = "Utente non registrato";
                return responseService.InternalError(response);
            }

            UtenteDTO item = new UtenteDTO().convertFromModel(utente);

            return responseService.Ok(item);
        } catch (Exception ex)
        {
            return responseService.InternalError(response);
        }

    }
}
