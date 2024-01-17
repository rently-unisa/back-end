package it.unisa.c02.rently.rently_application.data.dto;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class AnnuncioDTO implements Serializable {

    private String nome;

    private String strada;

    private String citta;

    private String CAP;

    private String descrizione;

    private BigDecimal prezzo;

    private String immagine;

    private String categoria;

    private String condizione;

    private Date dataFine;

    private Long idUtente;

    public AnnuncioDTO convertFromModel(Annuncio a) {
        AnnuncioDTO item = new AnnuncioDTO();
        item.setNome(a.getNome());
        item.setStrada(a.getStrada());
        item.setCitta(a.getCitta());
        item.setCAP(a.getCAP());
        item.setDescrizione(a.getDescrizione());
        item.setPrezzo(a.getPrezzo());
        item.setImmagine(a.getImmagine());
        item.setCategoria(String.valueOf(a.getCategoria()));
        item.setCondizione(String.valueOf(a.getCondizione()));
        item.setDataFine(a.getDataFine());
        item.setIdUtente(a.getUtente().getId());

        return item;
    }
}
