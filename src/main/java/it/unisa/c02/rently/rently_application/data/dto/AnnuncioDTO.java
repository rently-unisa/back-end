package it.unisa.c02.rently.rently_application.data.dto;

import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class AnnuncioDTO implements Serializable {

    private long id;
    private String nome;

    private String strada;

    private String citta;

    private String cap;

    private String descrizione;

    private BigDecimal prezzo;

    private String immagine;

    private String categoria;

    private String condizione;

    private String dataFine;

    private Long idUtente;

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

    public void setServerImage(Annuncio a, String serverPath) {
        //Path path = Paths.get(serverPath, "annunci", String.valueOf(a.getId()), a.getImmagine());
        String path = String.format("%s/%s/%s/%s", serverPath, "annunci", String.valueOf(a.getId()), a.getImmagine());
        this.setImmagine(path);
    }
}
