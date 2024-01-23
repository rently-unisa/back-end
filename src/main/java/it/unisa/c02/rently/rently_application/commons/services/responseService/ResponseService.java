package it.unisa.c02.rently.rently_application.commons.services.responseService;

import org.springframework.http.ResponseEntity;

/**
 * Questa interfaccia definisce le specifiche per i servizi dedicati alla gestione delle risposte in formato JSON dei controller.
 */
public interface ResponseService {

    /**
     * Restituisce un ResponseEntity con status CREATED contenente la stringa JSON di data.
     *
     * @param data l'oggetto che si vuole trasfomare in JSON e restituire.
     * @return l'EntityResponse con status CREATED contente il JSON di data.
     */
     ResponseEntity<String> Ok(Object data);

    /**
     * Restituisce un ResponseEntity con status CREATED.
     *
     * @return l'EntityResponse con status CREATED.
     */
     ResponseEntity<String> Ok();

    /**
     * Restituisce un ResponseEntity con status INTERNAL_SERVER_ERROR contenente la stringa JSON di data.
     *
     * @param data l'oggetto che si vuole trasfomare in JSON e restituire.
     * @return l'EntityResponse con status INTERNAL_SERVER_ERROR contente il JSON di data.
     */
     ResponseEntity<String> InternalError(Object data);

    /**
     * Restituisce un ResponseEntity con status INTERNAL_SERVER_ERROR.
     *
     * @return l'EntityResponse con status INTERNAL_SERVER_ERROR.
     */
     ResponseEntity<String> InternalError();


}
