package it.unisa.c02.rently.rently_application.data.dto;

import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtenteDTO {

    public UtenteDTO() {

    }

    public UtenteDTO(long id, String username, String nome, String cognome, String email, String password,
                     String nuovaPassword, String confermaNuovaPassword, boolean premium) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.nuovaPassword = nuovaPassword;
        this.confermaNuovaPassword = confermaNuovaPassword;
        this.premium = premium;
    }

    public UtenteDTO convertFromModel(Utente u)
    {
        UtenteDTO item = new UtenteDTO();
        item.setId(u.getId());
        item.setUsername(u.getUsername());
        item.setNome(u.getNome());
        item.setCognome(u.getCognome());
        item.setEmail(u.getEmail());
        item.setPremium(u.isPremium());
        return item;
    }

    @Override
    public String toString() {
        return "UtenteDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", premium=" + premium +
                '}';
    }

    private long id;

    private String username;

    private String nome;

    private String cognome;

    private String email;

    private String password;
    private String nuovaPassword;
    private String confermaNuovaPassword;

    private boolean premium;
}
