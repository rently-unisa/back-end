package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Messaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO che definisce le operazioni di accesso dati per la gestione della chat.
 */
@Repository
public interface GestioneChatDAO extends JpaRepository<Messaggio, Long> {

    /**
     * Recupera la cronologia dei messaggi scambiati tra due utenti specificati.
     *
     * @param id1 Identificativo del primo utente.
     * @param id2 Identificativo del secondo utente.
     * @return Lista di messaggi scambiati tra i due utenti.
     */
    @Query("SELECT m FROM Messaggio m WHERE m.mittente = (?1) AND m.destinatario = (?2) UNION" +
            " SELECT m FROM Messaggio m WHERE m.mittente = (?2) AND m.destinatario = (?1)")
    List<Messaggio> getChat(long id1, long id2);
}
