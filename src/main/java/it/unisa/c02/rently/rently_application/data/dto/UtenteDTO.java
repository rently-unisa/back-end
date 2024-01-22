package it.unisa.c02.rently.rently_application.data.dto;

import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.Getter;
import lombok.Setter;

/**
 * Questa classe rappresenta un DTO dell'utente registrato alla piattaforma.
 */
@Getter
@Setter
public class UtenteDTO {

    /**
     * Costruttore senza argomenti.
     */
    public UtenteDTO() {

    }

    /**
     * Costruttore per la creazione di un nuovo utente con informazioni complete, compreso l'ID.
     * @param id ID univoco dell'utente.
     * @param username Username univoco dell'utente.
     * @param nome Nome dell'utente.
     * @param cognome Cognome dell'utente.
     * @param email Indirizzo email univoco.
     * @param password Password dell'utente.
     * @param premium Indica se l'utente è un utente premium.
     * @param confermaNuovaPassword se presente indica la conferma della nuova password.
     * @param nuovaPassword se presente indica la nuova password.
     * @param token token necessario per i servizi di GestioneAutenticazione
     */
    public UtenteDTO(long id, String username, String nome, String cognome, String email, String password,
                     String nuovaPassword, String confermaNuovaPassword, boolean premium,
                     String token) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.nuovaPassword = nuovaPassword;
        this.confermaNuovaPassword = confermaNuovaPassword;
        this.premium = premium;
        this.token = token;
    }

    /**
     * Converte un Utente in un UtenteDTO.
     * @param u Utente da convertire in DTO
     * @return il DTO convertito dal Utente
     */
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

    /**
     * Override del metodo toString per ottenere una rappresentazione testuale dell'oggetto UtenteDTO.
     * @return Stringa che rappresenta l'oggetto UtenteDTO.
     */
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

    /**
     * Rappresenta l'ID univoco di un utente registrato.
     */
    private long id;

    /**
     * Rappresenta l'username univoco di un utente registrato.
     */
    private String username;

    /**
     * Rappresenta il nome di un utente registrato.
     */
    private String nome;

    /**
     * Rappresenta il cognome di un utente registrato.
     */
    private String cognome;

    /**
     * Rappresenta l'email univoca di un utente registrato.
     */
    private String email;

    /**
     * Rappresenta la password di un utente registrato.
     */
    private String password;

    /**
     * Rappresenta la nuova password di un utente registrato.
     */
    private String nuovaPassword;

    /**
     * Rappresenta la conferma della nuova password di un utente registrato.
     */
    private String confermaNuovaPassword;

    /**
     * Indica se l'utente è un utente premium.
     */
    private boolean premium;

    /**
     * Rappresenta un token necessario per i servizi di autenticazione.
     */
    private String token;
}
