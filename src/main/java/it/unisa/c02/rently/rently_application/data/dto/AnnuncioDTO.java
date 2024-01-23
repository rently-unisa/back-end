package it.unisa.c02.rently.rently_application.data.dto;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * Questa classe rappresenta un DTO per un annuncio.
 */
@Getter
@Setter
public class AnnuncioDTO implements Serializable {

    /**
     * Rappresenta l'ID univoco di un annuncio.
     */
    private long id;

    /**
     * Rappresenta il nome dell'annuncio.
     */
    private String nome;

    /**
     * Rappresenta la strada dove ritirare l'oggetto dell'annuncio.
     */
    private String strada;

    /**
     * Rappresenta la cottà dove si trova l'oggetto dell'annuncio.
     */
    private String citta;

    /**
     * Rappresenta il Codice di Avviamento Postale (CAP) dell'annuncio.
     */
    private String cap;

    /**
     * Rappresenta la descrizione dettagliata dell'annuncio.
     */
    private String descrizione;

    /**
     * Rappresenta il prezzo di noleggio giornaliero dell'annuncio.
     */
    private BigDecimal prezzo;

    /**
     * Rappresenta l'URL dell'immagine dell'oggetto dell'annuncio.
     */
    private String immagine;

    /**
     * Rappresenta la categoria dell'oggetto dell'annuncio.
     */
    private String categoria;

    /**
     * Rappresenta la condizione dell'oggetto dell'annuncio
     */
    private String condizione;

    /**
     * Rappresenta la data di fine disponibilità dell'oggetto dell'annuncio.
     */
    private String dataFine;

    /**
     * Rappresenta l'utente che ha pubblicato l'annuncio.
     */
    private Long idUtente;


    /**
     * Costruttore per la creazione di un nuovo annuncio.
     * @param nome Nome dell'annuncio.
     * @param strada Nome della strada dove ritirare l'oggetto dell'annuncio.
     * @param citta Città dove si trova l'oggetto dell'annuncio.
     * @param cap Codice di Avviamento Postale (CAP) dell'annuncio.
     * @param descrizione Descrizione dettagliata dell'oggetto dell'annuncio.
     * @param prezzo Prezzo di noleggio giornaliero dell'oggetto dell'annuncio.
     * @param immagine URL dell'immagine rappresentativa dell'oggetto dell'annuncio.
     * @param categoria Categoria dell'oggetto dell'annuncio.
     * @param condizione Condizione dell'oggetto dell'annuncio.
     * @param dataFine Data di fine disponibilità dell'oggetto dell'annuncio.
     * @param idUtente Utente che ha pubblicato l'annuncio.
     */
    public AnnuncioDTO(long id, String nome, String strada, String citta, String cap, String descrizione, BigDecimal prezzo, String immagine, String categoria, String condizione, String dataFine, Long idUtente) {
        this.id = id;
        this.nome = nome;
        this.strada = strada;
        this.citta = citta;
        this.cap = cap;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.immagine = immagine;
        this.categoria = categoria;
        this.condizione = condizione;
        this.dataFine = dataFine;
        this.idUtente = idUtente;
    }

    /**
     * Costruttore senza argomenti.
     */
    public AnnuncioDTO() {
    }

    /**
     * Converte un Annuncio in un AnnuncioDTO.
     * @param a Annuncio da convertire in DTO
     * @return il DTO convertito dall'annuncio
     */
    public AnnuncioDTO convertFromModel(Annuncio a) {
        AnnuncioDTO item = new AnnuncioDTO();
        item.setId(a.getId());
        item.setNome(a.getNome());
        item.setStrada(a.getStrada());
        item.setCitta(a.getCitta());
        item.setCap(a.getCap());
        item.setDescrizione(a.getDescrizione());
        item.setPrezzo(a.getPrezzo());
        item.setImmagine(a.getImmagine());
        item.setCategoria(String.valueOf(a.getCategoria()));
        item.setCondizione(String.valueOf(a.getCondizione()));
        item.setDataFine(a.getDataFine().toString());
        item.setIdUtente(a.getUtente().getId());

        return item;
    }


    /**
     * Crea il path dell'immagine dell'annuncio passato in input
     * @param a Annuncio a cui appartiene l'immagine
     * @param serverPath il path assoluto del server
     */
    public void setServerImage(Annuncio a, String serverPath) {
        //Path path = Paths.get(serverPath, "annunci", String.valueOf(a.getId()), a.getImmagine());
        String path = String.format("%s/%s/%s/%s", serverPath, "annunci", String.valueOf(a.getId()), a.getImmagine());
        this.setImmagine(path);
    }
}
