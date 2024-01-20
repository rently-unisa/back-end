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

        private final GestioneAreaPersonaleService areaPersonaleService;
        private final ResponseService responseService;
        private final GestioneChatService chatService;

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
