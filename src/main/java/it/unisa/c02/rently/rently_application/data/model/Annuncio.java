package it.unisa.c02.rently.rently_application.data.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Annuncio {

    public Annuncio() {
    }

    public Annuncio(String nome, String strada, String citta, String CAP, String descrizione, BigDecimal prezzo, String immagine, EnumCategoria categoria, EnumCondizione condizione, Date dataFine, Utente utente, List<Noleggio> noleggi, List<ValutazioneOggetto> valutazioni) {
        this.nome = nome;
        this.strada = strada;
        this.citta = citta;
        this.CAP = CAP;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.immagine = immagine;
        this.categoria = categoria;
        this.condizione = condizione;
        this.dataFine = dataFine;
        this.utente = utente;
        this.noleggi = noleggi;
        this.valutazioni = valutazioni;
    }

    public Annuncio(long id, String nome, String strada, String citta, String CAP, String descrizione, BigDecimal prezzo, String immagine, EnumCategoria categoria, EnumCondizione condizione, Date dataFine, Utente utente, List<Noleggio> noleggi, List<ValutazioneOggetto> valutazioni) {
        this.id = id;
        this.nome = nome;
        this.strada = strada;
        this.citta = citta;
        this.CAP = CAP;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.immagine = immagine;
        this.categoria = categoria;
        this.condizione = condizione;
        this.dataFine = dataFine;
        this.utente = utente;
        this.noleggi = noleggi;
        this.valutazioni = valutazioni;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="annuncio_id")
    private long id;

    @Column(length=100, nullable = false)
    private String nome;

    @Column(length=100, nullable = false)
    private String strada;

    @Column(length=100, nullable = false)
    private String citta;

    @Column(length=5, nullable = false)
    private String CAP;

    @Column(length=1023, nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private BigDecimal prezzo;

    @Column(length=255, nullable = false)
    private String immagine;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Annuncio.EnumCategoria categoria;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Annuncio.EnumCondizione condizione;

    @Column(nullable = false)
    private Date dataFine;

    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Utente utente;

    @OneToMany(mappedBy="annuncio")
    private List<Noleggio> noleggi;

    @OneToMany(mappedBy="annuncio")
    private List<ValutazioneOggetto> valutazioni;


    public enum EnumCategoria {
        ELETTRONICA, LIBRI, ELETTRODOMESTICI, GIARDINO, GIARDINAGGIO, ARTE, MUSICA, CASAECUCINA, AUTOEMOTO, OGGETTISTICAPROFESSIONALE, SPORT
    }

    public enum EnumCondizione {
        OTTIMA, BUONA, DISCRETA
    }

    @Override
    public String toString() {
        return "Annuncio{" +
                "nome='" + nome + '\'' +
                ", strada='" + strada + '\'' +
                ", citta='" + citta + '\'' +
                ", CAP='" + CAP + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", prezzo=" + prezzo +
                ", immagine='" + immagine + '\'' +
                ", categoria=" + categoria +
                ", condizione=" + condizione +
                ", dataFine=" + dataFine +
                ", utente=" + utente +
                '}';
    }
}