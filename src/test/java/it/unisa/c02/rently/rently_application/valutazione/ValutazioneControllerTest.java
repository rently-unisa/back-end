package it.unisa.c02.rently.rently_application.valutazione;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisa.c02.rently.rently_application.business.gestioneNoleggio.service.GestioneNoleggioService;
import it.unisa.c02.rently.rently_application.business.gestioneValutazione.service.GestioneValutazioneService;
import it.unisa.c02.rently.rently_application.data.dto.ValutazioneDTO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneUtente;
import org.springframework.security.test.context.support.WithMockUser;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;
import java.sql.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
public class ValutazioneControllerTest {


    @MockBean
    private GestioneValutazioneService valutazioneService;

    /**
     * Service per effettuare le operazioni di persistenza.
     */
    @MockBean
    private GestioneAreaPersonaleService areaPersonaleService;

    /**
     * Service per effettuare le operazioni di persistenza.
     */
    @MockBean
    private GestioneNoleggioService noleggioService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     *
     * @throws Exception
     */
    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    public void testAggiungiValutazioneUtente() throws Exception {

        ValutazioneDTO valutazioneUtenteDTO = new ValutazioneDTO(8, "Transazione veloce e comunicazione cordiale.", 1, 2, 1);

        Utente mockUtente1 = new Utente(1, "user1", "Mario", "Rossi", "mario.rossi@email.com", "password123", false);
        Utente mockUtente2 = new Utente(2, "user2", "Luca", "Bianchi", "luca.bianchi@email.com", "securePass", false);

        given(areaPersonaleService.getDatiPrivati(1)).willReturn(mockUtente1);
        given(areaPersonaleService.getDatiPrivati(2)).willReturn(mockUtente1);

        Annuncio annuncio1 = new Annuncio(1l, "Smartphone Samsung", "Via Roma", "Milan", "20121", "Telefono in ottime condizioni", new BigDecimal("30.00"), "img1.jpg", Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2024-10-05"), mockUtente1, null, null);
        Noleggio mockNoleggio = new Noleggio(1, Noleggio.EnumStato.CONCLUSO, new BigDecimal("100.00"), Date.valueOf("2024-02-01"), Date.valueOf("2024-02-07"), Date.valueOf("2024-01-28"), mockUtente1, mockUtente2, annuncio1);

        given(noleggioService.getNoleggio(1)).willReturn(mockNoleggio);

        ValutazioneUtente valutazioneUtente = new ValutazioneUtente(8, "Transazione veloce e comunicazione cordiale.", mockUtente1, mockUtente2, mockNoleggio);

        given(valutazioneService.addValutazioneUtente(any(ValutazioneUtente.class))).willReturn(valutazioneUtente);

        mockMvc.perform(post("/api/valutazione/aggiungi-valutazione-utente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(valutazioneUtenteDTO)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.descrizione", is(valutazioneUtente.getDescrizione())))
                .andExpect(jsonPath("$.voto", is(valutazioneUtente.getVoto())));

    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    public void testAggiungiValutazioneUtenteReturnServerErrorVoto() throws Exception {

        ValutazioneDTO valutazioneUtenteDTO = new ValutazioneDTO(15, "Transazione veloce e comunicazione cordiale.", 1, 2, 1);

        Utente mockUtente1 = new Utente(1, "user1", "Mario", "Rossi", "mario.rossi@email.com", "password123", false);
        Utente mockUtente2 = new Utente(2, "user2", "Luca", "Bianchi", "luca.bianchi@email.com", "securePass", false);

        given(areaPersonaleService.getDatiPrivati(1)).willReturn(mockUtente1);
        given(areaPersonaleService.getDatiPrivati(2)).willReturn(mockUtente1);

        Annuncio annuncio1 = new Annuncio(1l, "Smartphone Samsung", "Via Roma", "Milan", "20121", "Telefono in ottime condizioni", new BigDecimal("30.00"), "img1.jpg", Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2024-10-05"), mockUtente1, null, null);
        Noleggio mockNoleggio = new Noleggio(1, Noleggio.EnumStato.CONCLUSO, new BigDecimal("100.00"), Date.valueOf("2024-02-01"), Date.valueOf("2024-02-07"), Date.valueOf("2024-01-28"), mockUtente1, mockUtente2, annuncio1);

        given(noleggioService.getNoleggio(1)).willReturn(mockNoleggio);

        mockMvc.perform(post("/api/valutazione/aggiungi-valutazione-utente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(valutazioneUtenteDTO)))
                .andExpect(status().is5xxServerError());

    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    public void testAggiungiValutazioneUtenteReturnServerError() throws Exception {

        ValutazioneDTO valutazioneUtenteDTO = new ValutazioneDTO(15, "Transazione veloce e comunicazione cordiale.", 1, 2, 1);

        Utente mockUtente1 = null;
        Utente mockUtente2 = new Utente(2, "user2", "Luca", "Bianchi", "luca.bianchi@email.com", "securePass", false);

        given(areaPersonaleService.getDatiPrivati(1)).willReturn(mockUtente1);
        given(areaPersonaleService.getDatiPrivati(2)).willReturn(mockUtente1);

        Annuncio annuncio1 = new Annuncio(1l, "Smartphone Samsung", "Via Roma", "Milan", "20121", "Telefono in ottime condizioni", new BigDecimal("30.00"), "img1.jpg", Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2024-10-05"), mockUtente1, null, null);
        Noleggio mockNoleggio = new Noleggio(1, Noleggio.EnumStato.CONCLUSO, new BigDecimal("100.00"), Date.valueOf("2024-02-01"), Date.valueOf("2024-02-07"), Date.valueOf("2024-01-28"), mockUtente1, mockUtente2, annuncio1);

        given(noleggioService.getNoleggio(1)).willReturn(mockNoleggio);

        mockMvc.perform(post("/api/valutazione/aggiungi-valutazione-utente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(valutazioneUtenteDTO)))
                .andExpect(status().is5xxServerError());

    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    public void testAggiungiValutazioneUtenteReturnServerErrorRegex() throws Exception{

        ValutazioneDTO valutazioneUtenteDTO = new ValutazioneDTO(15, "", 1, 2, 1);

        Utente mockUtente1 = new Utente(1, "user1", "Mario", "Rossi", "mario.rossi@email.com", "password123", false);
        Utente mockUtente2 = new Utente(2, "user2", "Luca", "Bianchi", "luca.bianchi@email.com", "securePass", false);

        given(areaPersonaleService.getDatiPrivati(1)).willReturn(mockUtente1);
        given(areaPersonaleService.getDatiPrivati(2)).willReturn(mockUtente1);

        Annuncio annuncio1 = new Annuncio(1l, "Smartphone Samsung", "Via Roma", "Milan", "20121", "Telefono in ottime condizioni", new BigDecimal("30.00"), "img1.jpg", Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2024-10-05"), mockUtente1, null, null);
        Noleggio mockNoleggio = new Noleggio(1, Noleggio.EnumStato.CONCLUSO, new BigDecimal("100.00"), Date.valueOf("2024-02-01"), Date.valueOf("2024-02-07"), Date.valueOf("2024-01-28"), mockUtente1, mockUtente2, annuncio1);

        given(noleggioService.getNoleggio(1)).willReturn(mockNoleggio);

        mockMvc.perform(post("/api/valutazione/aggiungi-valutazione-utente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(valutazioneUtenteDTO)))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message", is("Errore durante l'inserimento dei dati")));

    }

}