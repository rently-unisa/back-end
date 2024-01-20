package it.unisa.c02.rently.rently_application.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Questa classe rappresenta un utente registrato alla piattaforma.
 */
@Entity
@Getter
@Setter
public class Utente {

    /**
     * Costruttore senza argomenti.
     */
    public Utente() {
    }

    /**
     * Costruttore per la creazione di un nuovo utente.
     * @param username Username univoco dell'utente.
     * @param nome Nome dell'utente.
     * @param cognome Cognome dell'utente.
     * @param email Indirizzo email univoco.
     * @param password Password dell'utente.
     * @param premium Indica se l'utente è un utente premium.
     */
    public Utente(String username, String nome, String cognome, String email, String password, boolean premium) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.premium = premium;
    }

    /**
     * Costruttore per la creazione di un nuovo utente con informazioni complete, compreso l'ID.
     * @param id ID univoco dell'utente.
     * @param username Username univoco dell'utente.
     * @param nome Nome dell'utente.
     * @param cognome Cognome dell'utente.
     * @param email Indirizzo email univoco.
     * @param password Password dell'utente.
     * @param premium Indica se l'utente è un utente premium.
     */
    public Utente(long id, String username, String nome, String cognome, String email, String password, boolean premium) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.premium = premium;
    }

    /**
     * Rappresenta l'ID univoco di un utente registrato.
     */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="utente_id")
    private long id;

    /**
     * Rappresenta l'username univoco di un utente registrato.
     */
    @Column(length=100, unique=true, nullable = false)
    private String username;

    /**
     * Rappresenta il nome di un utente registrato.
     */
    @Column(length=100, nullable = false)
    private String nome;

    /**
     * Rappresenta il cognome di un utente registrato.
     */
    @Column(length=100, nullable = false)
    private String cognome;

    /**
     * Rappresenta l'email univoca di un utente registrato.
     */
    @Column(length=100, unique=true, nullable = false)
    private String email;

    /**
     * Rappresenta la password di un utente registrato.
     */
    @Column(length=64, nullable = false)
    private String password;

    /**
     * Indica se l'utente è un utente premium.
     */
    @Column(nullable = false)
    private boolean premium;

    /**
     * Rappresenta la lista delle segnalazioni effettuate da un utente.
     */
    @OneToMany(mappedBy="segnalatore")
    private List<Segnalazione> segnalazioni;

    /**
     * Rappresenta la lista delle valutazioni ricevute dall'utente.
     */
    @OneToMany(mappedBy="valutato")
    private List<ValutazioneUtente> valutazioniRicevute;

    /**
     * Rappresenta la lista delle valutazioni effettuate dall'utente.
     */
    @OneToMany(mappedBy="valutatore")
    private List<ValutazioneUtente> valutazioniEffettuate;

    /**
     * Rappresenta la lista delle valutazioni sugli oggetti effettuate dall'utente.
     */
    @OneToMany(mappedBy="valutatore")
    private List<ValutazioneOggetto> valutazioniOggettoEffettuate;

    /**
     * Rappresenta la lista degli annunci pubblicati dall'utente.
     */
    @OneToMany(mappedBy="utente")
    private List<Annuncio> annunci;

    /**
     * Rappresenta la lista dei noleggi in cui l'utente è il noleggiante.
     */
    @OneToMany(mappedBy="noleggiante")
    private List<Noleggio> noleggiPresi;

    /**
     * Rappresenta la lista dei noleggi in cui l'utente è il noleggiatore.
     */
    @OneToMany(mappedBy="noleggiatore")
    private List<Noleggio> noleggiDati;

    /**
     * Override del metodo toString per ottenere una rappresentazione testuale dell'oggetto Utente.
     * @return Stringa che rappresenta l'oggetto Utente.
     */
    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", premium=" + premium +
                '}';
    }
}