package it.unisa.c02.rently.rently_application.business.gestioneChat.service;

import it.unisa.c02.rently.rently_application.data.dao.GestioneChatDAO;
import it.unisa.c02.rently.rently_application.data.model.Messaggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Implementazione del servizio di gestione della chat.
 * Questa classe fornisce implementazioni concrete per i metodi dichiarati nell'interfaccia GestioneChatService.
 */
@Service
@RequiredArgsConstructor
public class GestioneChatServiceImpl implements GestioneChatService {

    /**
     * L'istanza di GestioneChatDAO utilizzata per l'accesso ai dati dei messaggi.
     *
     */
    private final GestioneChatDAO gestioneChatDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public Messaggio addMessaggio(Messaggio messaggio) {
        return gestioneChatDAO.save(messaggio);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Messaggio> getChat(Utente id1, Utente id2) {
        return gestioneChatDAO.getChat(id1, id2);
    }
}
