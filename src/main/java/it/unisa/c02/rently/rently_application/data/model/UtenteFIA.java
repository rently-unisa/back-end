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
public class UtenteFIA {


    public UtenteFIA() {
    }

    public UtenteFIA(Utente idUtente, String categoria) {
        this.idUtente = idUtente;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "UtenteFIA{" +
                "id=" + id +
                ", idUtente=" + idUtente +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "utente_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utente idUtente;

    @Column( nullable = true)
    private String categoria;
}