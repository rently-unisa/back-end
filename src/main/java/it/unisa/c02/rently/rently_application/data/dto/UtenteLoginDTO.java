package it.unisa.c02.rently.rently_application.data.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Questa classe rappresenta un DTO delle credenziali usate dall'utente per registrarsi alla piattaforma.
 */
@Getter
@Setter
public class UtenteLoginDTO {

    /**
     * Rappresenta l'email univoca di un utente registrato.
     */
    private String email;

    /**
     * Rappresenta la password di un utente registrato.
     */
    private String password;
}
