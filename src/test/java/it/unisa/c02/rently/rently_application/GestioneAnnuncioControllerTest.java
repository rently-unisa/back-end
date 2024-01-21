package it.unisa.c02.rently.rently_application;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service.GestioneAnnuncioService;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.commons.services.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.commons.services.storageService.FilesStorageService;
import it.unisa.c02.rently.rently_application.data.dto.AnnuncioDTO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
@RunWith(SpringRunner.class)
public class GestioneAnnuncioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GestioneAnnuncioService gestioneAnnuncioService;

    @MockBean
    private GestioneAreaPersonaleService gestioneAreaPersonaleService;

    @MockBean
    private ResponseService responseService;

    @MockBean
    private FilesStorageService storageService;

    @Autowired
    private ObjectMapper objectMapper;

    public GestioneAnnuncioControllerTest() {

    }
    @Test
    public void givenAnnuncioObject_whenAnnuncio_thenReturnSavedAnnuncio() throws Exception{

        System.out.println("Prova");
        // given - precondition or setup
        AnnuncioDTO annuncio = new AnnuncioDTO(1,"Smartphone Samsung", "Via Roma", "Milan",
                "20121", "Telefono in ottime condizioni",
                new BigDecimal("30.00"), "img1.jpg",
                String.valueOf(Annuncio.EnumCategoria.ELETTRONICA),
                String.valueOf(Annuncio.EnumCondizione.OTTIMA), "2024-10-05", 1l);

        given(gestioneAnnuncioService.addAnnuncio(any(Annuncio.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/api/annuncio/aggiungi-annuncio")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(annuncio)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome",
                        is(annuncio.getNome())))
                .andExpect(jsonPath("$.prezzo",
                        is(annuncio.getPrezzo())))
                .andExpect(jsonPath("$.categoria",
                        is(annuncio.getCategoria())));

    }

}