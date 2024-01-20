package it.unisa.c02.rently.rently_application.business.gestioneChat.service;

import it.unisa.c02.rently.rently_application.data.dao.GestioneChatDAO;
import it.unisa.c02.rently.rently_application.data.model.Messaggio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GestioneChatServiceImpl implements GestioneChatService {

    private final GestioneChatDAO gestioneChatDAO;

    @Override
    public Messaggio addMessaggio(Messaggio messaggio) {
        return gestioneChatDAO.save(messaggio);
    }

    @Override
    public List<Messaggio> getChat(long id1, long id2) {
        return gestioneChatDAO.getChat(id1, id2);
    }
}
