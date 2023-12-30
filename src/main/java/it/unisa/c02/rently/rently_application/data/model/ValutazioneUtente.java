package it.unisa.c02.rently.rently_application.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
public class ValutazioneUtente {

    public ValutazioneUtente() {
    }

    public ValutazioneUtente(int voto, String descrizione, Utente valutato, Utente valutatore) {
        this.voto = voto;
        this.descrizione = descrizione;
        this.valutato = valutato;
        this.valutatore = valutatore;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private int voto;

    @Column(length=255, nullable = false)
    private String descrizione;

    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utente valutato;

    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Utente valutatore;

    @Override
    public String toString() {
        return "ValutazioneUtente{" +
                "voto=" + voto +
                ", descrizione='" + descrizione + '\'' +
                ", valutato=" + valutato +
                ", valutatore=" + valutatore +
                '}';
    }
}