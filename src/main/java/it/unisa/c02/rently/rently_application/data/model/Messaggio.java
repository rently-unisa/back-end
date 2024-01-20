package it.unisa.c02.rently.rently_application.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

/**
 * Questa classe rappresenta un messaggio inviato sulla piattaforma.
 */
@Entity
@Getter
@Setter
public class Messaggio {

    /**
     * Costruttore senza argomenti.
     */
    public Messaggio() {
    }

    /**
     * Costruttore per la creazione di un nuovo messaggio.
     * @param descrizione Descrizione del messaggio.
     * @param orarioInvio Timestamp che indica l'orario di invio del messaggio.
     * @param mittente Utente mittente del messaggio.
     * @param destinatario Utente destinatario del messaggio.
     */
    public Messaggio(String descrizione, Timestamp orarioInvio, Utente mittente, Utente destinatario) {
        this.descrizione = descrizione;
        this.orarioInvio = orarioInvio;
        this.mittente = mittente;
        this.destinatario = destinatario;
    }

    /**
     * Rappresenta l'ID univoco del messaggio.
     */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Rappresenta la descrizione del messaggio.
     */
    @Column(length=2000, nullable = false)
    private String descrizione;

    /**
     * Rappresenta il timestamp che indica l'orario di invio del messaggio.
     */
    @Column(nullable = false)
    private java.sql.Timestamp orarioInvio;

    /**
     * Rappresenta l'utente mittente del messaggio.
     */
    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Utente mittente;

    /**
     * Rappresenta l'utente destinatario del messaggio.
     */
    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Utente destinatario;

    /**
     * Override del metodo toString per ottenere una rappresentazione testuale dell'oggetto Messaggio.
     * @return Stringa che rappresenta l'oggetto Messaggio.
     */
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