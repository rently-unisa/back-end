package it.unisa.c02.rently.rently_application.data.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Utente {

    public Utente() {
    }

    public Utente(String username, String nome, String cognome, String email, String password, boolean premium, List<Segnalazione> segnalazioni, List<ValutazioneUtente> valutazioniRicevute, List<ValutazioneUtente> valutazioniEffettuate, List<ValutazioneOggetto> valutazioniOggettoEffettuate, List<Annuncio> annunci, List<Noleggio> noleggiPresi, List<Noleggio> noleggiDati) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.premium = premium;
        this.segnalazioni = segnalazioni;
        this.valutazioniRicevute = valutazioniRicevute;
        this.valutazioniEffettuate = valutazioniEffettuate;
        this.valutazioniOggettoEffettuate = valutazioniOggettoEffettuate;
        this.annunci = annunci;
        this.noleggiPresi = noleggiPresi;
        this.noleggiDati = noleggiDati;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="utente_id")
    private long id;

    @Column(length=100, unique=true, nullable = false)
    private String username;

    @Column(length=100, nullable = false)
    private String nome;

    @Column(length=100, nullable = false)
    private String cognome;

    @Column(length=100, unique=true, nullable = false)
    private String email;

    @Column(length=64, nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean premium;

    @OneToMany(mappedBy="segnalatore")
    private List<Segnalazione> segnalazioni;

    @OneToMany(mappedBy="valutato")
    private List<ValutazioneUtente> valutazioniRicevute;

    @OneToMany(mappedBy="valutatore")
    private List<ValutazioneUtente> valutazioniEffettuate;

    @OneToMany(mappedBy="valutatore")
    private List<ValutazioneOggetto> valutazioniOggettoEffettuate;

    @OneToMany(mappedBy="utente")
    private List<Annuncio> annunci;

    @OneToMany(mappedBy="noleggiante")
    private List<Noleggio> noleggiPresi;

    @OneToMany(mappedBy="noleggiatore")
    private List<Noleggio> noleggiDati;

    @Override
    public String toString() {
        return "Utente{" +
                "username='" + username + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", premium=" + premium +
                '}';
    }
}