package it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service;


import it.unisa.c02.rently.rently_application.data.DTO.UtenteDTO;
import it.unisa.c02.rently.rently_application.data.dao.GestioneAreaPersonaleDAO;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GestioneAreaPersonaleServiceImpl implements GestioneAreaPersonaleService {

    private final GestioneAreaPersonaleDAO gestioneAreaPersonaleDAO;


    @Override
    public Utente updateUtente(Utente utente) {
        return gestioneAreaPersonaleDAO.save(utente);
    }

    @Override
    public UtenteDTO getDatiPrivati(long id) {
        Optional<Utente> optional = gestioneAreaPersonaleDAO.findById(id);
        if(optional.isPresent()) {
            Utente utente = optional.get();
            UtenteDTO DTO = new UtenteDTO(id,utente.getUsername(),utente.getNome(), utente.getCognome(),utente.getEmail(), utente.getPassword(), utente.isPremium());
            return DTO;
        }
        else
            return null;
    }




}
