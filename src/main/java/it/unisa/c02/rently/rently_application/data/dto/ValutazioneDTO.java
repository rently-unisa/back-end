package it.unisa.c02.rently.rently_application.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValutazioneDTO {

    public ValutazioneDTO() {

    }

    public ValutazioneDTO(int voto, String descrizione, long valutato, long valutatore) {
        this.voto = voto;
        this.descrizione = descrizione;
        this.valutato = valutato;
        this.valutatore = valutatore;
    }

    private int voto;

    private String descrizione;

    private long valutato;

    private long valutatore;

}
