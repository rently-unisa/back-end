package it.unisa.c02.rently.rently_application.autenticazione;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisa.c02.rently.rently_application.business.gestioneAutenticazione.service.GestioneAutenticazioneService;
import it.unisa.c02.rently.rently_application.data.dto.UtenteDTO;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class GestioneAutenticazioneControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GestioneAutenticazioneService autenticazioneService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
     void testSignUp() throws Exception {

        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setUsername("user1");
        utenteDTO.setNome("Mario");
        utenteDTO.setCognome("Rossi");
        utenteDTO.setPassword("Password@123");
        utenteDTO.setEmail("mario.rossi@email.com");
        utenteDTO.setPremium(false);

        Utente mockUtente = new Utente(1,"user1", "Mario", "Rossi", "mario.rossi@email.com", "password123", false);
        doNothing().when(autenticazioneService).signUp(any(Utente.class));

        mockMvc.perform(post("/api/autenticazione/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(utenteDTO)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.username", is(mockUtente.getUsername())));
    }

    @Test
     void testSignUpReturnServerError() throws Exception {

        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setUsername("user1");
        utenteDTO.setNome("Mario");
        utenteDTO.setCognome("Rossi");
        utenteDTO.setPassword("password123");
        utenteDTO.setEmail("mario.rossi@email.com");
        utenteDTO.setPremium(false);

        Mockito.doThrow(new RuntimeException()).when(autenticazioneService).signUp(any(Utente.class));

        mockMvc.perform(post("/api/autenticazione/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(utenteDTO)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
     void testModificaUtenteReturnServerErrorRegex() throws Exception {

        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setUsername("user1");
        utenteDTO.setNome("Mario");
        utenteDTO.setCognome("Rossi");
        utenteDTO.setPassword("password123");
        utenteDTO.setEmail("mario.rossiemail.com");
        utenteDTO.setPremium(false);

        mockMvc.perform(post("/api/autenticazione/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(utenteDTO)))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message", is("I dati inseriti non sono validi")));

    }
}