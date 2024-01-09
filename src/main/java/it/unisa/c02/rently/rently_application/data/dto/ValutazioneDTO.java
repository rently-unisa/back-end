package it.unisa.c02.rently.rently_application.data.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValutazioneDTO {

    public ValutazioneDTO() {

    }

    public ValutazioneDTO(int voto, String descrizione, long valutato) {
        this.voto = voto;
        this.descrizione = descrizione;
        this.valutato = valutato;
    }

    private int voto;

    private String descrizione;

    private long valutato;

}
