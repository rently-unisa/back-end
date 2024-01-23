package it.unisa.c02.rently.rently_application.data.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Questa classe rappresenta un noleggio sulla piattaforma.
 */
@Entity
@Getter
@Setter
public class Noleggio {

    /**
     * Costruttore senza argomenti.
     */
    public Noleggio() {
    }

    /**
     * Costruttore per la creazione di un nuovo noleggio.
     * @param stato Stato del noleggio.
     * @param prezzoTotale Prezzo totale del noleggio.
     * @param dataInizio Data di inizio del noleggio.
     * @param dataFine Data di fine del noleggio.
     * @param dataRichiesta Data di richiesta del noleggio.
     * @param noleggiante Utente che richiede il noleggio.
     * @param noleggiatore Utente che possiede l'oggetto del noleggio.
     * @param annuncio Annuncio associato al noleggio.
     */
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

    /**
     * Costruttore per la creazione di un nuovo noleggio con informazioni complete, compreso l'ID.
     * @param id ID univoco del noleggio
     * @param stato Stato del noleggio.
     * @param prezzoTotale Prezzo totale del noleggio.
     * @param dataInizio Data di inizio del noleggio.
     * @param dataFine Data di fine del noleggio.
     * @param dataRichiesta Data di richiesta del noleggio.
     * @param noleggiante Utente che richiede il noleggio.
     * @param noleggiatore Utente che possiede l'oggetto del noleggio.
     * @param annuncio Annuncio associato al noleggio.
     */
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

    /**
     * Rappresenta l'ID univoco di un noleggio.
     */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="noleggio_id")
    private long id;

    /**
     * Rappresenta lo stato del noleggio.
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Noleggio.EnumStato stato;

    /**
     * Rappresenta il prezzo totale del noleggio.
     */
    @Column(nullable = false)
    private BigDecimal prezzoTotale;

    /**
     * Rappresenta la data di inizio del noleggio.
     */
    @Column(nullable = false)
    private Date dataInizio;

    /**
     * Rappresenta la data di fine del noleggio.
     */
    @Column(nullable = false)
    private Date dataFine;

    /**
     * Rappresenta la data di richiesta del noleggio.
     */
    @Column(nullable = false)
    private Date dataRichiesta;

    /**
     * Rappresenta l'utente che richiede il noleggio.
     */
    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Utente noleggiante;

    /**
     * Rappresenta l'utente che possiede l'oggetto del noleggio.
     */
    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Utente noleggiatore;

    /**
     * Rappresenta l'annuncio associato al noleggio.
     */
    @ManyToOne
    @JoinColumn(referencedColumnName = "annuncio_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Annuncio annuncio;

    /**
     * Rappresenta la lista delle valutazioni dell'oggetto associate al noleggio.
     */
    @OneToMany(mappedBy="noleggio")
    private List<ValutazioneOggetto> valutazioniOggetto;

    /**
     * Rappresenta la lista delle valutazioni dell'utente associate al noleggio.
     */
    @OneToMany(mappedBy="noleggio")
    private List<ValutazioneUtente> valutazioniUtente;

    /**
     * Enumerazione dei possibili tipi di stato.
     */
    public enum EnumStato {
        RICHIESTA, ACCETTATA, RIFIUTATA, INIZIO, IN_CORSO, FINE, CONCLUSO, CONCLUSOCONVALUTAZIONE
    }

    /**
     * Override del metodo toString per ottenere una rappresentazione testuale dell'oggetto Noleggio.
     * @return Stringa che rappresenta l'oggetto Noleggio.
     */
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