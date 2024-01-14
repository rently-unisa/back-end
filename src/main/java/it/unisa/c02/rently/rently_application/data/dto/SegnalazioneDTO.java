package it.unisa.c02.rently.rently_application.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SegnalazioneDTO {

    public SegnalazioneDTO() {

    }

    public SegnalazioneDTO(String tipo, String contenuto, long segnalatore) {
        this.tipo = tipo;
        this.contenuto = contenuto;
        this.segnalatore = segnalatore;
    }

    private String tipo;

    private String contenuto;

    private long segnalatore;
}
