package it.unisa.c02.rently.rently_application.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Messaggio {

    public Messaggio() {
    }

    public Messaggio(String descrizione, Timestamp orarioInvio, Utente mittente, Utente destinatario) {
        this.descrizione = descrizione;
        this.orarioInvio = orarioInvio;
        this.mittente = mittente;
        this.destinatario = destinatario;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length=2000, nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private java.sql.Timestamp orarioInvio;

    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utente mittente;

    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utente destinatario;

    @Override
    public String toString() {
        return "Messaggio{" +
                "descrizione='" + descrizione + '\'' +
                ", orarioInvio=" + orarioInvio +
                ", mittente=" + mittente +
                ", destinatario=" + destinatario +
                '}';
    }
}