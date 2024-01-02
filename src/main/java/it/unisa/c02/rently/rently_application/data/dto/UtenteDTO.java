package it.unisa.c02.rently.rently_application.data.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtenteDTO {

    public UtenteDTO() {

    }

    public UtenteDTO(long id, String username, String nome, String cognome, String email, String password, boolean premium) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.premium = premium;
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

    private boolean premium;
}
