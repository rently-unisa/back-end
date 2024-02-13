package it.unisa.c02.rently.rently_application.business.ModuloFIA.service;

import it.unisa.c02.rently.rently_application.data.dto.UtenteFiaDTO;
import it.unisa.c02.rently.rently_application.data.model.Utente;

public interface ModuloFIAService {

    boolean RichiestaFlask(UtenteFiaDTO utenteDTO, Utente Utente);

    String getCategoria(Utente utente);
}
