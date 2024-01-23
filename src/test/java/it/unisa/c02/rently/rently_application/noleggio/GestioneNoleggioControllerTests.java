package it.unisa.c02.rently.rently_application.noleggio;

import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.business.gestioneNoleggio.service.GestioneNoleggioService;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class GestioneNoleggioControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GestioneAreaPersonaleService areaPersonaleService;

    @MockBean
    private GestioneNoleggioService noleggioService;

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    public void testGetRichiesteByNoleggiante() {
        try {
            Utente mockUtente1 = new Utente(1, "user1", "Mario", "Rossi", "mario.rossi@email.com", "password123", false);
            Utente mockUtente2 = new Utente(2, "user2", "Luca", "Bianchi", "luca.bianchi@email.com", "securePass", false);
            Annuncio mockAnnuncio1 = new Annuncio(1, "Smartphone Samsung", "Via Roma", "Milan", "20121", "Telefono in ottime condizioni", new BigDecimal("30.00"), "img1.jpg", Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2024-10-05"), mockUtente1, null, null);

            given(areaPersonaleService.getDatiPrivati(1)).willReturn(mockUtente1);

            Noleggio mockNoleggio1 = new Noleggio(3, Noleggio.EnumStato.RICHIESTA, new BigDecimal("100.00"), Date.valueOf("2024-02-01"), Date.valueOf("2024-02-07"), Date.valueOf("2024-01-28"), mockUtente1, mockUtente2, mockAnnuncio1);
            Noleggio mockNoleggio2 = new Noleggio(2, Noleggio.EnumStato.CONCLUSO, new BigDecimal("200.00"), Date.valueOf("2024-02-10"), Date.valueOf("2024-02-15"), Date.valueOf("2024-02-07"), mockUtente1, mockUtente2, mockAnnuncio1);

            List<Noleggio> list = new ArrayList<>();
            list.add(mockNoleggio1);
            list.add(mockNoleggio2);

            given(noleggioService.getRichiesteByNoleggiante(any(Utente.class))).willReturn(list);

            mockMvc.perform(get("/api/noleggio/richieste/noleggiante")
                            .param("idUtente", "1"))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(jsonPath("$[0].stato", is("RICHIESTA")));
        }catch(Exception ex){

            ex.printStackTrace();
        }
    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    public void testGetRichiesteByNoleggianteReturnServerError() throws Exception {

        Utente mockUtente1 = null;
        Utente mockUtente2 = new Utente(2, "user2", "Luca", "Bianchi", "luca.bianchi@email.com", "securePass", false);
        Annuncio mockAnnuncio1 = new Annuncio(1, "Smartphone Samsung", "Via Roma", "Milan", "20121", "Telefono in ottime condizioni", new BigDecimal("30.00"), "img1.jpg", Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2024-10-05"), mockUtente1, null, null);

        given(areaPersonaleService.getDatiPrivati(1)).willReturn(mockUtente1);

        mockMvc.perform(get("/api/noleggio/richieste/noleggiante")
                        .param("idUtente", "1"))
                .andExpect(status().is5xxServerError());
    }

}

