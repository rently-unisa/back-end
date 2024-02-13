package it.unisa.c02.rently.rently_application.business.ModuloFIA.service;

import it.unisa.c02.rently.rently_application.commons.jsonHelper.JsonHelper;
import it.unisa.c02.rently.rently_application.data.dao.UtenteFIADAO;
import it.unisa.c02.rently.rently_application.data.dto.UtenteFiaDTO;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import it.unisa.c02.rently.rently_application.data.model.UtenteFIA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class ModuloFIAServiceImpl implements ModuloFIAService {

    private final UtenteFIADAO fiaDAO;
    private JsonHelper jsonhelper= new JsonHelper();

    private final String flaskUrl = "http://localhost:5000/predictCategoria";

    public boolean RichiestaFlask(UtenteFiaDTO utenteDTO, Utente Utente) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonBody = jsonhelper.getJsonFromObject(utenteDTO);

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(flaskUrl, requestEntity, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {

            String responseBody = responseEntity.getBody();
            String categoria_predetta = jsonhelper.formJsonToStringFIA(responseBody);

            switch (categoria_predetta) {
                case "Cat_1":
                    categoria_predetta= "ELETTRONICA";
                    break;
                case "Cat_2":
                    categoria_predetta= "LIBRI";
                    break;
                case "Cat_3":
                    categoria_predetta= "ELETTRODOMESTICI";
                    break;
                case "Cat_4":
                    categoria_predetta= "CASAECUCINA";
                    break;
                case "Cat_5":
                    categoria_predetta= "SPORT";
                    break;
                case "Cat_6":
                    categoria_predetta= "ARTE";
                    break;
                case "Cat_7":
                    categoria_predetta= "GIARDINAGGIO";
                    break;
            }

            UtenteFIA utenteFia = new UtenteFIA(Utente, categoria_predetta);
            fiaDAO.save(utenteFia);
            return true;
        } else {
            return false;
        }
    }

    public String getCategoria(Utente utente){

        UtenteFIA utenteFIA = fiaDAO.findByIdUtente(utente);
        String categoria = utenteFIA.getCategoria();
        return categoria;
    }

}
