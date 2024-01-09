package it.unisa.c02.rently.rently_application.business.gestioneChat.service;

import it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service.GestioneAnnuncioService;
import it.unisa.c02.rently.rently_application.data.dao.GestioneAnnuncioDAO;
import it.unisa.c02.rently.rently_application.data.dao.GestioneChatDAO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Messaggio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GestioneChatServiceImpl implements GestioneChatService {

    private final GestioneChatDAO gestioneChatDAO;

    @Override
    public Messaggio addMessaggio(Messaggio messaggio) {
        return gestioneChatDAO.save(messaggio);
    }

    @Override
    public List<Messaggio> getChat(int id1, int id2) {
        return null;
    }
}
