package it.unisa.c02.rently.rently_application.data.dto;

import it.unisa.c02.rently.rently_application.data.model.Messaggio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessaggioDTO {

    public MessaggioDTO() {

    }
    private long id;

    private String descrizione;

    private String orarioInvio;

    private long mittente;

    private long destinatario;

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
