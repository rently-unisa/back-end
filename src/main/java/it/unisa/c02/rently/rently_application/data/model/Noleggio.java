package it.unisa.c02.rently.rently_application.data.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.sql.Date;


@Entity
@Getter
@Setter
public class Noleggio {

    public Noleggio() {
    }

    public Noleggio(EnumStato stato, BigDecimal prezzoTotale, Date dataInizio, Date dataFine, Utente noleggiante, Utente noleggiatore, Annuncio annuncio) {
        this.stato = stato;
        this.prezzoTotale = prezzoTotale;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.noleggiante = noleggiante;
        this.noleggiatore = noleggiatore;
        this.annuncio = annuncio;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Noleggio.EnumStato stato;

    @Column(nullable = false)
    private BigDecimal prezzoTotale;

    @Column(nullable = false)
    private Date dataInizio;

    @Column(nullable = false)
    private Date dataFine;

    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Utente noleggiante;

    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Utente noleggiatore;

    @ManyToOne
    @JoinColumn(referencedColumnName = "annuncio_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Annuncio annuncio;

    public enum EnumStato {
        RICHIESTA, ACCETTATA, RIFIUTATA, INIZIO, IN_CORSO, FINE, CONCLUSO
    }

    @Override
    public String toString() {
        return "Noleggio{" +
                "stato=" + stato +
                ", prezzoTotale=" + prezzoTotale +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", noleggiante=" + noleggiante +
                ", noleggiatore=" + noleggiatore +
                ", annuncio=" + annuncio +
                '}';
    }
}