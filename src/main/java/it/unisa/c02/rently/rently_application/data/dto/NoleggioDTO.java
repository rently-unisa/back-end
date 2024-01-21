package it.unisa.c02.rently.rently_application.data.dto;

import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class NoleggioDTO {


    private Long id;

    private String stato;

    private BigDecimal prezzoTotale;

    private String dataInizio;

    private String dataFine;

    private String dataRichiesta;

    private Long noleggiante;

    private Long noleggiatore;

    private Long annuncio;

    private boolean valutazioneAlNoleggiante;

    private boolean valutazioneAlNoleggiatore;

    private boolean valutazioneAnnuncio;

    public NoleggioDTO convertFromModel(Noleggio n) {
        NoleggioDTO item = new NoleggioDTO();
        item.setId(n.getId());
        item.setStato(String.valueOf(n.getStato()));
        item.setPrezzoTotale(n.getPrezzoTotale());
        item.setDataInizio(n.getDataInizio().toString());
        item.setDataFine(n.getDataFine().toString());
        item.setDataRichiesta(n.getDataRichiesta().toString());
        item.setNoleggiante(n.getNoleggiante().getId());
        item.setNoleggiatore(n.getNoleggiatore().getId());
        item.setAnnuncio(n.getAnnuncio().getId());

        return item;
    }
}
