package it.unisa.c02.rently.rently_application.business.gestioneChat.service;

import it.unisa.c02.rently.rently_application.data.model.Messaggio;
import java.util.List;

public interface GestioneChatService {


    Messaggio addMessaggio(Messaggio messaggio);
    List<Messaggio> getChat(long id1, long id2);
}
