package it.unisa.c02.rently.rently_application.commons.jsonHelper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import it.unisa.c02.rently.rently_application.data.model.Utente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JsonHelper {

    public String getJsonFromObject(Object data) {
        String json = "";

        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(data);
        } catch (Exception ex) {
        }
        return json;
    }
}
