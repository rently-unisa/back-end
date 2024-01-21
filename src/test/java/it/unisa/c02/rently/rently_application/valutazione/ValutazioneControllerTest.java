package it.unisa.c02.rently.rently_application.valutazione;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service.GestioneAnnuncioService;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.business.gestioneNoleggio.service.GestioneNoleggioService;
import it.unisa.c02.rently.rently_application.business.gestioneValutazione.service.GestioneValutazioneService;
import it.unisa.c02.rently.rently_application.commons.services.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.commons.services.storageService.FilesStorageService;
import it.unisa.c02.rently.rently_application.data.dto.AnnuncioDTO;
import it.unisa.c02.rently.rently_application.data.dto.ValutazioneDTO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneUtente;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ValutazioneControllerTest {


    @Autowired
    private GestioneValutazioneService valutazioneService;

    /**
     * Service per effettuare le operazioni di persistenza.
     */
    @MockBean
    private GestioneAreaPersonaleService areaPersonaleService;


    /**
     * Service per la gestione delle risposte alle richieste.
     */
    @MockBean
    private ResponseService responseService;

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
    public void givenValutazioneObject_whenValutazione_thenReturnValutazione() throws Exception {

        try {
            System.out.println("Parto");
            // given - precondition or setup

            ValutazioneDTO valutazioneUtenteDTO = new ValutazioneDTO(8, "Transazione veloce e comunicazione cordiale.", 1l, 2l, 3l);

            Utente u1 = new Utente();
            u1.setId(1);

            Utente u2 = new Utente();
            u2.setId(1);

            Noleggio n = new Noleggio();
            n.setId(1);

            ValutazioneUtente valutazioneUtenteModel = new ValutazioneUtente(8,
                    "Transazione veloce e comunicazione cordiale.", u1, u2, n);

            // when - action or behaviour that we are going test
            ResultActions response = mockMvc.perform(post("/api/valutazione/aggiungi-valutazione-utente")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(valutazioneUtenteDTO)));

            // then - verify the result or output using assert statements
            response.andDo(print()).
                    andExpect(status().is2xxSuccessful());
        } catch (Exception ex)
        {
        }

    }

}