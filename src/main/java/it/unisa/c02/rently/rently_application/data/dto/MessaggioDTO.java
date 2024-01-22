package it.unisa.c02.rently.rently_application.data.dto;

import it.unisa.c02.rently.rently_application.data.model.Messaggio;
import lombok.Getter;
import lombok.Setter;

/**
 * Questa classe rappresenta il DTO di un messaggio inviato sulla piattaforma.
 */
@Getter
@Setter
public class MessaggioDTO {

    /**
     * Costruttore senza argomenti.
     */
    public MessaggioDTO() {
        //Costruttore vuoto
    }

    /**
     * Rappresenta l'ID univoco del messaggio.
     */
    private long id;

    /**
     * Rappresenta la descrizione del messaggio.
     */
    private String descrizione;

    /**
     * Rappresenta il timestamp che indica l'orario di invio del messaggio.
     */
    private String orarioInvio;

    /**
     * Rappresenta l'utente mittente del messaggio.
     */
    private long mittente;

    /**
     * Rappresenta l'utente destinatario del messaggio.
     */
    private long destinatario;

    /**
     * Converte un Messaggio in un MessaggiDTO.
     * @param m messaggio da convertire in DTO
     * @return il DTO convertito dal messaggio
     */
    public MessaggioDTO convertFromModel(Messaggio m) {
        MessaggioDTO item = new MessaggioDTO();
        item.setDescrizione(m.getDescrizione());
        item.setId(m.getId());
        item.setOrarioInvio(String.valueOf(m.getOrarioInvio()));
        item.setDestinatario(m.getDestinatario().getId());
        item.setMittente(m.getMittente().getId());

        return item;
    }


}
