package it.unisa.c02.rently.rently_application.business.gestioneChat.service;

import it.unisa.c02.rently.rently_application.data.model.Messaggio;

import java.util.List;
import java.util.Optional;

public interface GestioneChatService {


    Messaggio addMessaggio(Messaggio messaggio);
    List<Messaggio> getChat(int id1, int id2);
}
