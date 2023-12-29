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
        Segnalatore = segnalatore;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumTipo tipo;

    @Column(length=255, nullable = false)
    private String contenuto;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "utente_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utente Segnalatore;

    @Override
    public String toString() {
        return "Segnalazione{" +
                "tipo=" + tipo +
                ", contenuto='" + contenuto + '\'' +
                ", Segnalatore=" + Segnalatore +
                '}';
    }

    public enum EnumTipo {
        OGGETTODANNEGGIATO, RITARDOCONSEGNA, RITARDORESTITUZIONE, OGGETTOSMARRITO
    }
}