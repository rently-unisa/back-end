package it.unisa.c02.rently.rently_application.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
public class Segnalazione {

    public Segnalazione() {
    }

    public Segnalazione(EnumTipo tipo, String contenuto, Utente segnalatore) {
        this.tipo = tipo;
        this.contenuto = contenuto;
        this.segnalatore = segnalatore;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumTipo tipo;

    @Column(length=255, nullable = false)
    private String contenuto;

    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Utente segnalatore;

    @Override
    public String toString() {
        return "Segnalazione{" +
                "tipo=" + tipo +
                ", contenuto='" + contenuto + '\'' +
                ", segnalatore=" + segnalatore +
                '}';
    }

    public enum EnumTipo {
        OGGETTODANNEGGIATO, RITARDOCONSEGNA, RITARDORESTITUZIONE, OGGETTOSMARRITO
    }
}