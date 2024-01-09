package it.unisa.c02.rently.rently_application.commons.responseService;

import org.springframework.http.ResponseEntity;

public interface ResponseService {
    public ResponseEntity<String> Ok(Object data);
    public ResponseEntity<String> Ok();
    public ResponseEntity<String> InternalError(Object data);
    public ResponseEntity<String> InternalError();

}
