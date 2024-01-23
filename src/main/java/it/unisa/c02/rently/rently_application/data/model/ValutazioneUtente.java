package it.unisa.c02.rently.rently_application.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Questa classe rappresenta la valutazione di un utente che ha concluso un noleggio sulla piattaforma.
 */
@Entity
@Getter
@Setter
public class ValutazioneUtente {

    /**
     * Costruttore senza argomenti.
     */
    public ValutazioneUtente() {
    }

    /**
     * Costruttore per la creazione di una nuova valutazione di un utente.
     * @param voto Voto assegnato all'utente.
     * @param descrizione Descrizione della valutazione.
     * @param valutato Utente che è stato valutato.
     * @param valutatore Utente che ha effettuato la valutazione.
     * @param noleggio Noleggio associato all'annuncio.
     */
    public ValutazioneUtente(int voto, String descrizione, Utente valutato, Utente valutatore, Noleggio noleggio) {
        this.voto = voto;
        this.descrizione = descrizione;
        this.valutato = valutato;
        this.valutatore = valutatore;
        this.noleggio= noleggio;
    }

    /**
     * Costruttore per la creazione di una nuova valutazione di un utente con informazioni complete, compreso l'ID.
     * @param id ID univoco della valutazione di un utente.
     * @param voto Voto assegnato all'utente.
     * @param descrizione Descrizione della valutazione.
     * @param valutato Utente che è stato valutato.
     * @param valutatore Utente che ha effettuato la valutazione.
     * @param noleggio Noleggio associato all'annuncio.
     */
    public ValutazioneUtente(long id, int voto, String descrizione, Utente valutato, Utente valutatore, Noleggio noleggio) {
        this.id = id;
        this.voto = voto;
        this.descrizione = descrizione;
        this.valutato = valutato;
        this.valutatore = valutatore;
        this.noleggio= noleggio;
    }

    /**
     * Rappresenta l'ID univoco della valutazione dell'utente.
     */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Rappresenta il voto assegnato all'utente.
     */
    @Column(nullable = false)
    private int voto;

    /**
     * Rappresenta la descrizione della valutazione.
     */
    @Column(length=255, nullable = false)
    private String descrizione;

    /**
     * Rappresenta l'utente che è stato valutato.
     */
    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utente valutato;

    /**
     * Rappresenta l'utente che ha effettuato la valutazione.
     */
    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Utente valutatore;

    /**
     * Rappresenta il noleggio associato all'annuncio.
     */
    @ManyToOne
    @JoinColumn(referencedColumnName = "noleggio_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Noleggio noleggio;

    /**
     * Override del metodo toString per ottenere una rappresentazione testuale dell'oggetto ValutazioneUtente.
     * @return Stringa che rappresenta l'oggetto ValutazioneUtente.
     */
    @Override
    public String toString() {
        return "ValutazioneUtente{" +
                "voto=" + voto +
                ", descrizione='" + descrizione + '\'' +
                ", valutato=" + valutato +
                ", valutatore=" + valutatore +
                ", noleggio=" + noleggio +
                '}';
    }
}