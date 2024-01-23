package it.unisa.c02.rently.rently_application.commons.services.responseService;

import it.unisa.c02.rently.rently_application.commons.jsonHelper.JsonHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


/**
 * Implementazione del servizio di gestione delle risposte in formato JSON.
 * Questa classe fornisce implementazioni concrete per i metodi dichiarati nell'interfaccia ResponseService.
 */
@Service
public class ResponseServiceImpl implements ResponseService {

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<String> Ok() {
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<String> InternalError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

}