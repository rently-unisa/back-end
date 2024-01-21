package it.unisa.c02.rently.rently_application.business.gestioneChat.controller;

import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.business.gestioneChat.service.GestioneChatService;
import it.unisa.c02.rently.rently_application.commons.services.regexService.RegexTester;
import it.unisa.c02.rently.rently_application.commons.services.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.data.dto.MessaggioDTO;
import it.unisa.c02.rently.rently_application.data.dto.ResponseDTO;
import it.unisa.c02.rently.rently_application.data.model.Messaggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Questa classe gestisce le richieste relative ai messaggi attraverso i servizi forniti da
 * GestioneChatService, GestioneAreaPersonaleService e ResponseService.
 * Fornisce endpoint RESTful per aggiungere e visualizzare i messaggi.
 * Le risposte sono gestite utilizzando il servizio ResponseService per costruire risposte standardizzate in formato JSON.
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
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
public class GestioneChatController {

        /**
         * Service per effettuare le operazioni di persistenza.
         */
        private final GestioneAreaPersonaleService areaPersonaleService;

        /**
         * Service per la gestione delle risposte alle richieste.
         */
        private final ResponseService responseService;

        /**
         * Service per effettuare le operazioni di persistenza.
         */
        private final GestioneChatService chatService;


        /**
         * Endpoint per aggiungere un messaggio.
         *
         * @param messaggioDTO Dati del messaggio da aggiungere.
         * @return ResponseEntity contenente l'esito dell'operazione.
         */

        @PostMapping("/aggiungi-messaggio")
        public ResponseEntity<String> aggiungiMessaggio(@RequestBody MessaggioDTO messaggioDTO) {

                ResponseDTO message = new ResponseDTO();
                message.message = "Il contenuto del messaggio è sbagliato";

                HashMap<String, String> tester = new HashMap<>();
                tester.put(messaggioDTO.getDescrizione(), "^[\\s\\S]{1,2000}$");

                RegexTester regexTester = new RegexTester();
                if (!regexTester.toTest(tester)) {
                        return responseService.InternalError(message);
                }

                Messaggio messaggio = new Messaggio();
                Utente mittente = areaPersonaleService.getDatiPrivati(messaggioDTO.getMittente());
                Utente destinatario = areaPersonaleService.getDatiPrivati(messaggioDTO.getDestinatario());

                messaggio.setDestinatario(destinatario);
                messaggio.setMittente(mittente);
                messaggio.setDescrizione(messaggioDTO.getDescrizione());
                messaggio.setOrarioInvio(Timestamp.valueOf(messaggioDTO.getOrarioInvio()));

                if(messaggio.getDestinatario()!= null && messaggio.getMittente()!= null){
                        messaggio = chatService.addMessaggio(messaggio);
                        return responseService.Ok(messaggio);
                }
                else
                        return responseService.InternalError();
        }

        /**
         * Endpoint per visualizzare una chat tra due utenti.
         *
         * @param messaggioDTO contiene gli id degli utenti di cui visualizzare la chat.
         * @return ResponseEntity contenente l'esito dell'operazione.
         */
        @PostMapping("/visualizza-chat")
        public ResponseEntity<String> getChat (@RequestBody MessaggioDTO messaggioDTO){

                List<Messaggio> chat = chatService.getChat(messaggioDTO.getDestinatario(), messaggioDTO.getMittente());
                if(chat != null) {
                        List<MessaggioDTO> list = new ArrayList<>();
                        for (Messaggio m : chat) {
                                MessaggioDTO item = new MessaggioDTO().convertFromModel(m);
                                list.add(item);
                        }
                        return responseService.Ok(list);
                }else
                        return responseService.InternalError();
        }

}
