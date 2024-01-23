package it.unisa.c02.rently.rently_application.business.gestioneChat.service;

import it.unisa.c02.rently.rently_application.data.model.Messaggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;

import java.util.List;

/**
 * Questa interfaccia definisce le specifiche per i servizi dedicati alla gestione della chat.
 */
public interface GestioneChatService {

    /**
     * Aggiunge un messaggio sulla piattaforma.
     *
     * @param messaggio messaggio da aggiungere.
     * @return Messaggio aggiunto.
     */
    Messaggio addMessaggio(Messaggio messaggio);

    /**
     * Restituisce tutti i messaggi associati a due utenti.
     *
     * @param id1 id di uno degli utenti a cui appartiene la chat.
     * @param id2 id dell'altro utente a cui appartiene la chat.
     * @return Lista di tutti i messaggi associati a due utenti.
     */
    List<Messaggio> getChat(Utente id1, Utente id2);
}
