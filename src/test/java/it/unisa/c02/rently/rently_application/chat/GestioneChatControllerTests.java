package it.unisa.c02.rently.rently_application.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.business.gestioneChat.service.GestioneChatService;
import it.unisa.c02.rently.rently_application.data.dto.MessaggioDTO;
import it.unisa.c02.rently.rently_application.data.model.Messaggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class GestioneChatControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GestioneAreaPersonaleService areaPersonaleService;

    @MockBean
    private GestioneChatService chatService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
     void testAggiungiMessaggio() throws Exception {

        MessaggioDTO messaggioDTO = new MessaggioDTO();
        messaggioDTO.setDescrizione("Contenuto del messaggio");
        messaggioDTO.setOrarioInvio("2024-01-21 12:34:56");
        messaggioDTO.setMittente(1);
        messaggioDTO.setDestinatario(2);


        Utente mockUtente1 = new Utente(1,"user1", "Mario", "Rossi", "mario.rossi@email.com", "password123", false);
        given(areaPersonaleService.getDatiPrivati(1)).willReturn(mockUtente1);

        Utente mockUtente2 =new Utente(2,"user2", "Luca", "Bianchi", "luca.bianchi@email.com", "securePass", false);
        given(areaPersonaleService.getDatiPrivati(2)).willReturn(mockUtente1);

        Messaggio mockMessaggio = new Messaggio("Contenuto del messaggio", Timestamp.valueOf("2024-01-21 12:34:56"),mockUtente1,mockUtente2);
        given(chatService.addMessaggio(any(Messaggio.class))).willReturn(mockMessaggio);

        // Esegui la richiesta POST simulando l'invio del corpo JSON
        mockMvc.perform(post("/api/chat/aggiungi-messaggio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(messaggioDTO)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.descrizione", is(mockMessaggio.getDescrizione())));


    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
     void testAggiungiMessaggioReturnServerError() throws Exception {

        MessaggioDTO messaggioDTO = new MessaggioDTO();
        messaggioDTO.setDescrizione("Contenuto del messaggio");
        messaggioDTO.setOrarioInvio("2024-01-21 12:34:56");
        messaggioDTO.setMittente(1);
        messaggioDTO.setDestinatario(2);


        Utente mockUtente1 = null;
        given(areaPersonaleService.getDatiPrivati(1)).willReturn(mockUtente1);

        Utente mockUtente2 =new Utente(2,"user2", "Luca", "Bianchi", "luca.bianchi@email.com", "securePass", false);
        given(areaPersonaleService.getDatiPrivati(2)).willReturn(mockUtente1);

        Messaggio mockMessaggio = new Messaggio("Contenuto del messaggio", Timestamp.valueOf("2024-01-21 12:34:56"),mockUtente1,mockUtente2);
        given(chatService.addMessaggio(any(Messaggio.class))).willReturn(mockMessaggio);

        // Esegui la richiesta POST simulando l'invio del corpo JSON
        mockMvc.perform(post("/api/chat/aggiungi-messaggio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(messaggioDTO)))
                .andExpect(status().is5xxServerError());

    }


    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
     void testAggiungiMessaggioReturnServerErrorRegex() throws Exception {

        MessaggioDTO messaggioDTO = new MessaggioDTO();
        messaggioDTO.setDescrizione("");
        messaggioDTO.setOrarioInvio("2024-01-21 12:34:56");
        messaggioDTO.setMittente(1);
        messaggioDTO.setDestinatario(2);


        Utente mockUtente1 = new Utente(1,"user1", "Mario", "Rossi", "mario.rossi@email.com", "password123", false);
        given(areaPersonaleService.getDatiPrivati(1)).willReturn(mockUtente1);

        Utente mockUtente2 =new Utente(2,"user2", "Luca", "Bianchi", "luca.bianchi@email.com", "securePass", false);
        given(areaPersonaleService.getDatiPrivati(2)).willReturn(mockUtente1);

        Messaggio mockMessaggio = new Messaggio("Contenuto del messaggio", Timestamp.valueOf("2024-01-21 12:34:56"),mockUtente1,mockUtente2);
        given(chatService.addMessaggio(any(Messaggio.class))).willReturn(mockMessaggio);


        mockMvc.perform(post("/api/chat/aggiungi-messaggio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(messaggioDTO)))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message", is("Il contenuto del messaggio è sbagliato")));

    }
}

