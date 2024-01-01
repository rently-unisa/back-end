package it.unisa.c02.rently.rently_application.data.dto;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class AnnuncioDTO {

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
}
