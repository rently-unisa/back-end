package it.unisa.c02.rently.rently_application.areaPersonale;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.test.context.support.WithMockUser;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.data.dto.UtenteDTO;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class GestioneAreaPersonaleControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GestioneAreaPersonaleService areaPersonaleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    public void testModificaUtenteConPassword() throws Exception {

        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setNome("Mario");
        utenteDTO.setCognome("Rossi");
        utenteDTO.setPassword("password123");
        utenteDTO.setNuovaPassword("passwordMario");
        utenteDTO.setConfermaNuovaPassword("passwordMario");
        utenteDTO.setEmail("mario.rossi@email.com");
        utenteDTO.setUsername("user1");
        utenteDTO.setPremium(false);
        utenteDTO.setId(1);


        Utente mockUtente1 = new Utente(1,"user1", "Mario", "Rossi", "mario.rossi@email.com", "password123", false);
        given(areaPersonaleService.getDatiPrivati(anyLong())).willReturn(mockUtente1);

        Utente mockUtente2 = new Utente(1,"user1", "Mario", "Rossi", "mario.rossi@email.com", "passwordMario", false);

        given(areaPersonaleService.updateUtente(any(Utente.class))).willReturn(mockUtente2);

        mockMvc.perform(post("/api/area-personale/modifica-dati-utente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(utenteDTO)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.password", is(mockUtente2.getPassword())));


    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    public void testModificaUtenteSenzaPassword() throws Exception {

        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setNome("Mario");
        utenteDTO.setCognome("Rossi");
        utenteDTO.setPassword("password123");
        utenteDTO.setNuovaPassword("");
        utenteDTO.setConfermaNuovaPassword("");
        utenteDTO.setEmail("mario.rossi@email.com");
        utenteDTO.setUsername("MarioUsername");
        utenteDTO.setPremium(false);
        utenteDTO.setId(1);


        Utente mockUtente1 = new Utente(1,"user1", "Mario", "Rossi", "mario.rossi@email.com", "password123", false);
        given(areaPersonaleService.getDatiPrivati(anyLong())).willReturn(mockUtente1);

        Utente mockUtente2 = new Utente(1,"MarioUsername", "Mario", "Rossi", "mario.rossi@email.com", "password123", false);

        given(areaPersonaleService.updateUtente(any(Utente.class))).willReturn(mockUtente2);

        mockMvc.perform(post("/api/area-personale/modifica-dati-utente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(utenteDTO)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.username", is(mockUtente2.getUsername())));
    }


    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    public void testModificaUtenteReturnServerError() throws Exception {

        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setNome("Mario");
        utenteDTO.setCognome("Rossi");
        utenteDTO.setPassword("password123");
        utenteDTO.setNuovaPassword("passwordMario");
        utenteDTO.setConfermaNuovaPassword("passwordMario");
        utenteDTO.setEmail("mario.rossi@email.com");
        utenteDTO.setUsername("user1");
        utenteDTO.setPremium(false);
        utenteDTO.setId(1);


        Utente mockUtente1 = null;
        given(areaPersonaleService.getDatiPrivati(anyLong())).willReturn(mockUtente1);

        given(areaPersonaleService.updateUtente(any(Utente.class))).willReturn(null);

        mockMvc.perform(post("/api/area-personale/modifica-dati-utente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(utenteDTO)))
                .andExpect(status().is5xxServerError());

    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    public void testModificaUtenteReturnServerErrorRegex() throws Exception {

        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setNome("Mario");
        utenteDTO.setCognome("Rossi");
        utenteDTO.setPassword("password123");
        utenteDTO.setNuovaPassword("passwordMario");
        utenteDTO.setConfermaNuovaPassword("passwordMario");
        utenteDTO.setEmail("mario.rossiemail.com");
        utenteDTO.setUsername("user1");
        utenteDTO.setPremium(false);
        utenteDTO.setId(1);



        mockMvc.perform(post("/api/area-personale/modifica-dati-utente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(utenteDTO)))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message", is("I parametri non rispettano le regex")));

    }
}

