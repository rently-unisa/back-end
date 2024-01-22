package it.unisa.c02.rently.rently_application.data.dto;

import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Questa classe rappresenta un noleggio sulla piattaforma.
 */
@Getter
@Setter
public class NoleggioDTO {

    /**
     * Rappresenta l'ID univoco di un noleggio.
     */
    private Long id;

    /**
     * Rappresenta lo stato del noleggio.
     */
    private String stato;

    /**
     * Rappresenta il prezzo totale del noleggio.
     */
    private BigDecimal prezzoTotale;

    /**
     * Rappresenta la data di inizio del noleggio.
     */
    private String dataInizio;

    /**
     * Rappresenta la data di fine del noleggio.
     */
    private String dataFine;

    /**
     * Rappresenta la data di richiesta del noleggio.
     */
    private String dataRichiesta;

    /**
     * Rappresenta l'utente che richiede il noleggio.
     */
    private Long noleggiante;

    /**
     * Rappresenta l'utente che possiede l'oggetto del noleggio.
     */
    private Long noleggiatore;

    /**
     * Rappresenta l'annuncio associato al noleggio.
     */
    private Long annuncio;

    /**
     * Indica se è presente una specifica valutazioneUtente per un certo noleggio.
     */
    private boolean valutazioneAlNoleggiante;

    /**
     * Indica se è presente una specifica valutazioneUtente per un certo noleggio.
     */
    private boolean valutazioneAlNoleggiatore;

    /**
     * Indica se è presente una specifica valutazioneOggetto per un certo noleggio.
     */
    private boolean valutazioneAnnuncio;


    /**
     * Converte un Noleggio in un NoleggioDTO.
     * @param n Noleggio da convertire in DTO
     * @return il DTO convertito dal Noleggio
     */
    public NoleggioDTO convertFromModel(Noleggio n) {
        NoleggioDTO item = new NoleggioDTO();
        item.setId(n.getId());
        item.setStato(String.valueOf(n.getStato()));
        item.setPrezzoTotale(n.getPrezzoTotale());
        item.setDataInizio(n.getDataInizio().toString());
        item.setDataFine(n.getDataFine().toString());
        item.setDataRichiesta(n.getDataRichiesta().toString());
        item.setNoleggiante(n.getNoleggiante().getId());
        item.setNoleggiatore(n.getNoleggiatore().getId());
        item.setAnnuncio(n.getAnnuncio().getId());

        return item;
    }
}
