package it.unisa.c02.rently.rently_application.data.dto;

import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class NoleggioDTO implements Serializable {

    private long id;

    private String stato;

    private BigDecimal prezzoTotale;

    private Date dataInizio;

    private Date dataFine;

    private long noleggiante;

    private long noleggiatore;

    private long annuncio;

    public NoleggioDTO convertFromModel(Noleggio n) {
        NoleggioDTO item = new NoleggioDTO();
        item.setId(n.getId());
        item.setStato(String.valueOf(n.getStato()));
        item.setPrezzoTotale(n.getPrezzoTotale());
        item.setDataInizio(n.getDataInizio());
        item.setDataFine(n.getDataFine());
        item.setNoleggiante(n.getNoleggiante().getId());
        item.setNoleggiatore(n.getNoleggiatore().getId());
        item.setAnnuncio(n.getAnnuncio().getId());

        return item;
    }
}
