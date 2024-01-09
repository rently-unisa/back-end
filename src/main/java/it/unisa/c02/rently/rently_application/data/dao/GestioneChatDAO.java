package it.unisa.c02.rently.rently_application.data.dao;

import it.unisa.c02.rently.rently_application.data.model.Messaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GestioneChatDAO extends JpaRepository<Messaggio, Long> {

    @Query("SELECT m FROM Messaggio m WHERE m.mittente = (?1) AND m.destinatario = (?2) UNION" +
            " SELECT m FROM Messaggio m WHERE m.mittente = (?2) AND m.destinatario = (?1)")
    List<Messaggio> getChat(int id1, int id2);
}
