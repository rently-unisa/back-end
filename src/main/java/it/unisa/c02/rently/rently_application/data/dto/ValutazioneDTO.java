package it.unisa.c02.rently.rently_application.data.dto;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneOggetto;
import it.unisa.c02.rently.rently_application.data.model.ValutazioneUtente;
import lombok.Getter;
import lombok.Setter;

/**
 * Questa classe rappresenta i DTO per la valutazione di un utente e di un annuncio.
 */
@Getter
@Setter
public class ValutazioneDTO {

    /**
     * Costruttore senza argomenti.
     */
    public ValutazioneDTO() {

    }

    /**
     * Costruttore per la creazione di una nuova valutazione di un utente.
     * @param voto Voto assegnato all'utente.
     * @param descrizione Descrizione della valutazione.
     * @param valutato id dell'utente che è stato valutato.
     * @param valutatore id dell'utente che ha effettuato la valutazione.
     * @param noleggio id del noleggio associato all'annuncio.
     */
    public ValutazioneDTO(int voto, String descrizione, long valutato, long valutatore, long noleggio) {
        this.voto = voto;
        this.descrizione = descrizione;
        this.valutato = valutato;
        this.valutatore = valutatore;
        this.noleggio = noleggio;
    }

    /**
     * Rappresenta il voto assegnato all'utente.
     */
    private int voto;

    /**
     * Rappresenta la descrizione della valutazione.
     */
    private String descrizione;

    /**
     * Rappresenta l'id dell'utente o dell'annuncio che è stato valutato.
     */
    private long valutato;

    /**
     * Rappresenta l'utente che ha effettuato la valutazione.
     */
    private long valutatore;

    /**
     * Rappresenta il noleggio associato all'annuncio.
     */
    private long noleggio;


    /**
     * Converte un ValutazioneOggetto in un ValutazioneDTO.
     * @param vo ValutazioneOggetto da convertire in DTO
     * @return il DTO convertito dal ValutazioneOggetto
     */
    public ValutazioneDTO convertFromValutazioneOggetto(ValutazioneOggetto vo) {
        ValutazioneDTO item = new ValutazioneDTO();
        item.setDescrizione(vo.getDescrizione());
        item.setVoto(vo.getVoto());
        item.setValutato(vo.getAnnuncio().getId());
        item.setValutatore(vo.getValutatore().getId());
        item.setNoleggio(vo.getNoleggio().getId());

        return item;
    }

    /**
     * Converte un ValutazioneUtente in un ValutazioneDTO.
     * @param vu ValutazioneUtente da convertire in DTO
     * @return il DTO convertito dal ValutazioneUtente
     */
    public ValutazioneDTO convertFromValutazioneUtente(ValutazioneUtente vu) {
        ValutazioneDTO item = new ValutazioneDTO();
        item.setDescrizione(vu.getDescrizione());
        item.setVoto(vu.getVoto());
        item.setValutato(vu.getValutato().getId());
        item.setValutatore(vu.getValutatore().getId());
        item.setNoleggio(vu.getNoleggio().getId());

        return item;
    }

}
