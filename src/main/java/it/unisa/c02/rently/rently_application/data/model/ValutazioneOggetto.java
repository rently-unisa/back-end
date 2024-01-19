package it.unisa.c02.rently.rently_application.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
public class ValutazioneOggetto {

    public ValutazioneOggetto() {
    }

    public ValutazioneOggetto(int voto, String descrizione, Annuncio annuncio, Utente valutatore, Noleggio noleggio) {
        this.voto = voto;
        this.descrizione = descrizione;
        this.annuncio = annuncio;
        this.valutatore = valutatore;
        this.noleggio= noleggio;
    }

    public ValutazioneOggetto(long id, int voto, String descrizione, Annuncio annuncio, Utente valutatore, Noleggio noleggio) {
        this.id = id;
        this.voto = voto;
        this.descrizione = descrizione;
        this.annuncio = annuncio;
        this.valutatore = valutatore;
        this.noleggio= noleggio;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private int voto;

    @Column(length=255, nullable = false)
    private String descrizione;

    @ManyToOne
    @JoinColumn(referencedColumnName = "annuncio_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Annuncio annuncio;

    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Utente valutatore;

    @ManyToOne
    @JoinColumn(referencedColumnName = "noleggio_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Noleggio noleggio;

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