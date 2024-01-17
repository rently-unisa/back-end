package it.unisa.c02.rently.rently_application.commons.services.responseService;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResponseService {
    public ResponseEntity<String> Ok(Object data);
    public ResponseEntity<String> Ok();
    public ResponseEntity<String> InternalError(Object data);
    public ResponseEntity<String> InternalError();
    public ResponseEntity<String> Unauthorized(Object data);
    public ResponseEntity<String> Unauthorized();

}
