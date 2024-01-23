package it.unisa.c02.rently.rently_application.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Questa classe rappresenta una segnalazione effettuata alla piattaforma.
 */
@Entity
@Getter
@Setter
public class Segnalazione {

    /**
     * Costruttore senza argomenti.
     */
    public Segnalazione() {
    }

    /**
     * Costruttore per la creazione di una nuova segnalazione.
     * @param tipo Tipo della segnalazione.
     * @param contenuto Contenuto della segnalazione.
     * @param segnalatore Utente che ha effettuato la segnalazione.
     */
    public Segnalazione(EnumTipo tipo, String contenuto, Utente segnalatore) {
        this.tipo = tipo;
        this.contenuto = contenuto;
        this.segnalatore = segnalatore;
    }

    /**
     * Rappresenta l'ID univoco della segnalazione.
     */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Rappresenta il tipo della segnalazione.
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumTipo tipo;

    /**
     * Rappresenta il contenuto della segnalazione.
     */
    @Column(length=255, nullable = false)
    private String contenuto;

    /**
     * Rappresenta l'utente che ha effettuato la segnalazione.
     */
    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Utente segnalatore;

    /**
     * Override del metodo toString per ottenere una rappresentazione testuale dell'oggetto Segnalazione.
     * @return Stringa che rappresenta l'oggetto Segnalazione.
     */
    @Override
    public String toString() {
        return "Segnalazione{" +
                "tipo=" + tipo +
                ", contenuto='" + contenuto + '\'' +
                ", segnalatore=" + segnalatore +
                '}';
    }

    /**
     * Enumerazione dei possibili tipi di segnalazione.
     */
    public enum EnumTipo {
        OGGETTODANNEGGIATO, RITARDOCONSEGNA, RITARDORESTITUZIONE, OGGETTOSMARRITO
    }
}