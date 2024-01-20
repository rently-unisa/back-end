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
 * Questa classe rappresenta un annuncio pubblicato sulla piattaforma.
 */
@Entity
@Getter
@Setter
public class Annuncio {

    /**
     * Costruttore senza argomenti.
     */
    public Annuncio() {
    }

    /**
     * Costruttore per la creazione di un nuovo annuncio.
     * @param nome Nome dell'annuncio.
     * @param strada Nome della strada dove ritirare l'oggetto dell'annuncio.
     * @param citta Città dove si trova l'oggetto dell'annuncio.
     * @param CAP Codice di Avviamento Postale (CAP) dell'annuncio.
     * @param descrizione Descrizione dettagliata dell'oggetto dell'annuncio.
     * @param prezzo Prezzo di noleggio giornaliero dell'oggetto dell'annuncio.
     * @param immagine URL dell'immagine rappresentativa dell'oggetto dell'annuncio.
     * @param categoria Categoria dell'oggetto dell'annuncio.
     * @param condizione Condizione dell'oggetto dell'annuncio.
     * @param dataFine Data di fine disponibilità dell'oggetto dell'annuncio.
     * @param utente Utente che ha pubblicato l'annuncio.
     * @param noleggi Lista dei noleggi associati all'annuncio.
     * @param valutazioni Lista delle valutazioni dell'oggetto dell'annuncio.
     */
    public Annuncio(String nome, String strada, String citta, String CAP, String descrizione, BigDecimal prezzo, String immagine, EnumCategoria categoria, EnumCondizione condizione, Date dataFine, Utente utente, List<Noleggio> noleggi, List<ValutazioneOggetto> valutazioni) {
        this.nome = nome;
        this.strada = strada;
        this.citta = citta;
        this.cap = CAP;
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

    /**
     * Costruttore per la creazione di un annuncio con informazioni complete, compreso l'ID.
     * @param id ID univoco dell'annuncio.
     * @param nome Nome dell'annuncio.
     * @param strada Nome della strada dove ritirare l'oggetto dell'annuncio.
     * @param citta Città dove si trova l'oggetto dell'annuncio.
     * @param CAP Codice di Avviamento Postale (CAP) dell'annuncio.
     * @param descrizione Descrizione dettagliata dell'oggetto dell'annuncio.
     * @param prezzo Prezzo di noleggio giornaliero dell'oggetto dell'annuncio.
     * @param immagine URL dell'immagine rappresentativa dell'oggetto dell'annuncio.
     * @param categoria Categoria dell'oggetto dell'annuncio.
     * @param condizione Condizione dell'oggetto dell'annuncio.
     * @param dataFine Data di fine disponibilità dell'oggetto dell'annuncio.
     * @param utente Utente che ha pubblicato l'annuncio.
     * @param noleggi Lista dei noleggi associati all'annuncio.
     * @param valutazioni Lista delle valutazioni dell'oggetto dell'annuncio.
     */
    public Annuncio(long id, String nome, String strada, String citta, String CAP, String descrizione, BigDecimal prezzo, String immagine, EnumCategoria categoria, EnumCondizione condizione, Date dataFine, Utente utente, List<Noleggio> noleggi, List<ValutazioneOggetto> valutazioni) {
        this.id = id;
        this.nome = nome;
        this.strada = strada;
        this.citta = citta;
        this.cap = CAP;
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

    /**
     * Rappresenta l'ID univoco di un annuncio.
     */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="annuncio_id")
    private long id;

    /**
     * Rappresenta il nome dell'annuncio.
     */
    @Column(length=100, nullable = false)
    private String nome;

    /**
     * Rappresenta la strada dove ritirare l'oggetto dell'annuncio.
     */
    @Column(length=100, nullable = false)
    private String strada;

    /**
     * Rappresenta la cottà dove si trova l'oggetto dell'annuncio.
     */
    @Column(length=100, nullable = false)
    private String citta;

    /**
     * Rappresenta il Codice di Avviamento Postale (CAP) dell'annuncio.
     */
    @Column(length=5, nullable = false)
    private String cap;

    /**
     * Rappresenta la descrizione dettagliata dell'annuncio.
     */
    @Column(length=1023, nullable = false)
    private String descrizione;

    /**
     * Rappresenta il prezzo di noleggio giornaliero dell'annuncio.
     */
    @Column(nullable = false)
    private BigDecimal prezzo;

    /**
     * Rappresenta l'URL dell'immagine dell'oggetto dell'annuncio.
     */
    @Column(length=255, nullable = true)
    private String immagine;

    /**
     * Rappresenta la categoria dell'oggetto dell'annuncio.
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Annuncio.EnumCategoria categoria;

    /**
     * Rappresenta la condizione dell'oggetto dell'annuncio
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Annuncio.EnumCondizione condizione;

    /**
     * Rappresenta la data di fine disponibilità dell'oggetto dell'annuncio.
     */
    @Column(nullable = false)
    private Date dataFine;

    /**
     * Rappresenta l'utente che ha pubblicato l'annuncio.
     */
    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Utente utente;

    /**
     * Rappresenta la lista dei noleggi associati all'annuncio.
     */
    @OneToMany(mappedBy="annuncio")
    private List<Noleggio> noleggi;

    /**
     * Rappresenta la lista delle valutazioni dell'oggetto dell'annuncio.
     */
    @OneToMany(mappedBy="annuncio")
    private List<ValutazioneOggetto> valutazioni;


    public enum EnumCategoria {
        ELETTRONICA, LIBRI, ELETTRODOMESTICI, GIARDINO, GIARDINAGGIO, ARTE, MUSICA, CASAECUCINA, AUTOEMOTO, OGGETTISTICAPROFESSIONALE, SPORT
    }

    /**
     * Enumerazione delle possibili categorie dell'oggetto dell'annuncio.
     */
    public enum EnumCondizione {
        OTTIMA, BUONA, DISCRETA
    }

    /**
     * Override del metodo toString per ottenere una rappresentazione testuale dell'oggetto Annuncio.
     * @return Stringa che rappresenta l'oggetto Annuncio.
     */
    @Override
    public String toString() {
        return "Annuncio{" +
                "nome='" + nome + '\'' +
                ", strada='" + strada + '\'' +
                ", citta='" + citta + '\'' +
                ", CAP='" + cap + '\'' +
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