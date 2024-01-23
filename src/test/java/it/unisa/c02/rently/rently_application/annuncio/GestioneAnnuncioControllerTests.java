package it.unisa.c02.rently.rently_application.annuncio;

import it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service.GestioneAnnuncioService;
import it.unisa.c02.rently.rently_application.commons.services.storageService.FilesStorageService;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;


import java.math.BigDecimal;
import java.sql.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class GestioneAnnuncioControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GestioneAreaPersonaleService areaPersonaleService;

    @MockBean
    private GestioneAnnuncioService gestioneAnnuncioService;

    @MockBean
    private FilesStorageService storageService;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
     void testAggiungiAnnuncio() throws Exception {

        MockMultipartFile imageFile = new MockMultipartFile(
                "image",
                "test-image.jpg",
                "image/jpeg",
                "img1".getBytes()
        );

        RequestBuilder request = multipart("/api/annuncio/aggiungi-annuncio").file(imageFile)
                .param("nome","Smartphone Samsung")
                .param("strada","Via Roma")
                .param("citta","Milan")
                .param("cap","20121")
                .param("descrizione","Telefono in ottime condizioni")
                .param("prezzo","30.00")
                .param("categoria","ELETTRONICA")
                .param("condizione","OTTIMA")
                .param("dataFine","2024-10-05")
                .param("idUtente","1");

        Utente mockUtente1 = new Utente(1,"user1", "Mario", "Rossi", "mario.rossi@email.com",
                "password123", false);

        given(areaPersonaleService.getDatiPrivati(anyLong())).willReturn(mockUtente1);

        Annuncio annunciomock = new Annuncio(1, "Smartphone Samsung", "Via Roma", "Milan", "20121",
                "Telefono in ottime condizioni", new BigDecimal("30.00"), "img1.jpg",
                Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2024-10-05"), mockUtente1, null, null);

        given(gestioneAnnuncioService.addAnnuncio(any(Annuncio.class))).willReturn(annunciomock);

        doNothing().when(storageService).init(any(String.class));

        given(storageService.generateRandomFileName()).willReturn("img1");
        doNothing().when(storageService).save(any(MultipartFile.class),any(String.class));

        MockMvc mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.nome", is(annunciomock.getNome())))
                .andExpect(jsonPath("$.strada", is(annunciomock.getStrada())));

    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
     void testAggiungiAnnuncioReturnServerError() throws Exception {

        MockMultipartFile imageFile = new MockMultipartFile(
                "image",
                "test-image.jpg",
                "image/jpeg",
                "img1".getBytes()
        );

        RequestBuilder request = multipart("/api/annuncio/aggiungi-annuncio").file(imageFile)
                .param("nome","Smartphone Samsung")
                .param("strada","Via Roma")
                .param("citta","Milan")
                .param("cap","20121")
                .param("descrizione","Telefono in ottime condizioni")
                .param("prezzo","30.00")
                .param("categoria","ELETTRONICA")
                .param("condizione","OTTIMA")
                .param("dataFine","2024-10-05")
                .param("idUtente","1");

        Utente mockUtente1 = null;

        given(areaPersonaleService.getDatiPrivati(anyLong())).willReturn(mockUtente1);

        Annuncio annunciomock = new Annuncio(1, "Smartphone Samsung", "Via Roma", "Milan", "20121",
                "Telefono in ottime condizioni", new BigDecimal("30.00"), "img1.jpg",
                Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2024-10-05"), mockUtente1, null, null);

        given(gestioneAnnuncioService.addAnnuncio(any(Annuncio.class))).willReturn(annunciomock).willThrow(new RuntimeException());

        doNothing().when(storageService).init(any(String.class));

        given(storageService.generateRandomFileName()).willReturn("img1");
        doNothing().when(storageService).save(any(MultipartFile.class),any(String.class));

        MockMvc mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


        mockMvc.perform(request)
                .andExpect(status().is5xxServerError());


    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
     void testAggiungiAnnuncioReturnServerErrorRegex() throws Exception {

        MockMultipartFile imageFile = new MockMultipartFile(
                "image",
                "test-image.jpg",
                "image/jpeg",
                "img1".getBytes()
        );

        RequestBuilder request = multipart("/api/annuncio/aggiungi-annuncio").file(imageFile)
                .param("nome","Smartphone Samsung")
                .param("strada","Via Roma")
                .param("citta","Milan")
                .param("cap","20121")
                .param("descrizione","Telefono in ottime condizioni")
                .param("prezzo","30000000000000.00000")
                .param("categoria","ELETTRONICA")
                .param("condizione","OTTIMA")
                .param("dataFine","2024-10-05")
                .param("idUtente","1");


        MockMvc mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


        mockMvc.perform(request)
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message", is("Dati inseriti non validi")));


    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
     void testAggiungiAnnuncioReturnServerErrorMissingData() throws Exception {

        MockMultipartFile imageFile = new MockMultipartFile(
                "image",
                "test-image.jpg",
                "image/jpeg",
                "img1".getBytes()
        );

        RequestBuilder request = multipart("/api/annuncio/aggiungi-annuncio").file(imageFile);


        Utente mockUtente1 = new Utente(1,"user1", "Mario", "Rossi", "mario.rossi@email.com",
                "password123", false);

        given(areaPersonaleService.getDatiPrivati(anyLong())).willReturn(mockUtente1);

        Annuncio annunciomock = new Annuncio(1, "Smartphone Samsung", "Via Roma", "Milan", "20121",
                "Telefono in ottime condizioni", new BigDecimal("30.00"), "img1.jpg",
                Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2024-10-05"), mockUtente1, null, null);

        given(gestioneAnnuncioService.addAnnuncio(any(Annuncio.class))).willReturn(annunciomock);

        doNothing().when(storageService).init(any(String.class));

        given(storageService.generateRandomFileName()).willReturn("img1");
        doNothing().when(storageService).save(any(MultipartFile.class),any(String.class));

        MockMvc mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


        mockMvc.perform(request)
                .andExpect(status().is5xxServerError());

    }

}

