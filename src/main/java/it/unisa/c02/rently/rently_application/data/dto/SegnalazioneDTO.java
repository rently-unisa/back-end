package it.unisa.c02.rently.rently_application.data.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Questa classe rappresenta il DTO di una segnalazione effettuata alla piattaforma.
 */
@Getter
@Setter
public class SegnalazioneDTO {

    /**
     * Costruttore senza argomenti.
     */
    public SegnalazioneDTO() {

    }

    /**
     * Costruttore per la creazione di una nuova segnalazione.
     * @param tipo Tipo della segnalazione.
     * @param contenuto Contenuto della segnalazione.
     * @param segnalatore Utente che ha effettuato la segnalazione.
     */
    public SegnalazioneDTO(String tipo, String contenuto, long segnalatore) {
        this.tipo = tipo;
        this.contenuto = contenuto;
        this.segnalatore = segnalatore;
    }


    /**
     * Rappresenta il tipo della segnalazione.
     */
    private String tipo;

    /**
     * Rappresenta il contenuto della segnalazione.
     */
    private String contenuto;

    /**
     * Rappresenta l'id dell'utente che ha effettuato la segnalazione.
     */
    private long segnalatore;
}
