package it.unisa.c02.rently.rently_application.data.dto;

import it.unisa.c02.rently.rently_application.data.model.ValutazioneOggetto;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneUtente;
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

    public ValutazioneDTO convertFromValutazioneOggetto(ValutazioneOggetto vo) {
        ValutazioneDTO item = new ValutazioneDTO();
        item.setDescrizione(vo.getDescrizione());
        item.setVoto(vo.getVoto());
        item.setValutato(vo.getAnnuncio().getId());
        item.setValutatore(vo.getValutatore().getId());

        return item;
    }

    public ValutazioneDTO convertFromValutazioneUtente(ValutazioneUtente vu) {
        ValutazioneDTO item = new ValutazioneDTO();
        item.setDescrizione(vu.getDescrizione());
        item.setVoto(vu.getVoto());
        item.setValutato(vu.getValutato().getId());
        item.setValutatore(vu.getValutatore().getId());

        return item;
    }

}
