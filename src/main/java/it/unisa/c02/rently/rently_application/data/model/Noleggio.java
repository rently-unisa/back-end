package it.unisa.c02.rently.rently_application.data.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


@Entity
@Getter
@Setter
public class Noleggio {

    public Noleggio() {
    }

    public Noleggio(EnumStato stato, BigDecimal prezzoTotale, Date dataInizio, Date dataFine, Date dataRichiesta, Utente noleggiante, Utente noleggiatore, Annuncio annuncio) {
        this.stato = stato;
        this.prezzoTotale = prezzoTotale;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.dataRichiesta = dataRichiesta;
        this.noleggiante = noleggiante;
        this.noleggiatore = noleggiatore;
        this.annuncio = annuncio;
    }

    public Noleggio(long id, EnumStato stato, BigDecimal prezzoTotale, Date dataInizio, Date dataFine, Date dataRichiesta, Utente noleggiante, Utente noleggiatore, Annuncio annuncio) {
        this.id = id;
        this.stato = stato;
        this.prezzoTotale = prezzoTotale;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.dataRichiesta = dataRichiesta;
        this.noleggiante = noleggiante;
        this.noleggiatore = noleggiatore;
        this.annuncio = annuncio;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="noleggio_id")
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

    @Column(nullable = false)
    private Date dataRichiesta;

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

    @OneToMany(mappedBy="noleggio")
    private List<ValutazioneOggetto> valutazioniOggetto;

    @OneToMany(mappedBy="noleggio")
    private List<ValutazioneUtente> valutazioniUtente;
    public enum EnumStato {
        RICHIESTA, ACCETTATA, RIFIUTATA, INIZIO, IN_CORSO, FINE, CONCLUSO, CONCLUSOCONVALUTAZIONE
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