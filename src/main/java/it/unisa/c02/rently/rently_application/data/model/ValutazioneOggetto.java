package it.unisa.c02.rently.rently_application.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Questa classe rappresenta la valutazione di un oggetto di un annuncio pubblicato sulla piattaforma.
 */
@Entity
@Getter
@Setter
public class ValutazioneOggetto {

    /**
     * Costruttore senza argomenti.
     */
    public ValutazioneOggetto() {
    }

    /**
     * Costruttore per la creazione di una nuova valutazione di un oggetto.
     * @param voto Voto assegnato all'oggetto nell'annuncio.
     * @param descrizione Descrizione della valutazione.
     * @param annuncio Annuncio a cui è associata la valutazione.
     * @param valutatore Utente che ha effettuato la valutazione.
     * @param noleggio Noleggio associato all'annuncio.
     */
    public ValutazioneOggetto(int voto, String descrizione, Annuncio annuncio, Utente valutatore, Noleggio noleggio) {
        this.voto = voto;
        this.descrizione = descrizione;
        this.annuncio = annuncio;
        this.valutatore = valutatore;
        this.noleggio= noleggio;
    }

    /**
     * Costruttore per la creazione di una nuova valutazione di un oggetto con informazioni complete, compreso l'ID.
     * @param id ID univoco della valutazione di un oggetto.
     * @param voto Voto assegnato all'oggetto nell'annuncio.
     * @param descrizione Descrizione della valutazione.
     * @param annuncio Annuncio a cui è associata la valutazione.
     * @param valutatore Utente che ha effettuato la valutazione.
     * @param noleggio Noleggio associato all'annuncio.
     */
    public ValutazioneOggetto(long id, int voto, String descrizione, Annuncio annuncio, Utente valutatore, Noleggio noleggio) {
        this.id = id;
        this.voto = voto;
        this.descrizione = descrizione;
        this.annuncio = annuncio;
        this.valutatore = valutatore;
        this.noleggio= noleggio;
    }

    /**
     * Rappresenta l'ID univoco della valutazione dell'oggetto.
     */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Rappresenta il voto assegnato all'oggetto nell'annuncio.
     */
    @Column(nullable = false)
    private int voto;

    /**
     * Rappresenta la descrizione della valutazione dell'oggetto.
     */
    @Column(length=255, nullable = false)
    private String descrizione;

    /**
     * Rappresenta l'annuncio a cui è associata la valutazione.
     */
    @ManyToOne
    @JoinColumn(referencedColumnName = "annuncio_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Annuncio annuncio;

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
     * Override del metodo toString per ottenere una rappresentazione testuale dell'oggetto ValutazioneOggetto.
     * @return Stringa che rappresenta l'oggetto ValutazioneOggetto.
     */
    @Override
    public String toString() {
        return "ValutazioneOggetto{" +
                "voto=" + voto +
                ", descrizione='" + descrizione + '\'' +
                ", annuncio=" + annuncio +
                ", valutatore=" + valutatore +
                ", noleggio=" + noleggio +
                '}';
    }
}