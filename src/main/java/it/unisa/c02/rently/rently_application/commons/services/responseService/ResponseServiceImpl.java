package it.unisa.c02.rently.rently_application.commons.services.responseService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import it.unisa.c02.rently.rently_application.commons.jsonHelper.JsonHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ResponseServiceImpl implements ResponseService {

    @Override
    public ResponseEntity<String> Ok(Object data) {
        String json = "";
        try {
            json = new JsonHelper().getJsonFromObject(data);
        }
        catch (Exception ex)
        {
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(json);
    }

    @Override
    public ResponseEntity<String> Ok() {
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

    @Override
    public ResponseEntity<String> InternalError(Object data) {
        String json = "";
        try {
            json = new JsonHelper().getJsonFromObject(data);
        }
        catch (Exception ex)
        {
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
    }

    @Override
    public ResponseEntity<String> InternalError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @Override
    public ResponseEntity<String> Unauthorized(Object data) {
        String json = "";
        try {
            json = new JsonHelper().getJsonFromObject(data);
        }
        catch (Exception ex)
        {
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
    }

    @Override
    public ResponseEntity<String> Unauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
    }
}