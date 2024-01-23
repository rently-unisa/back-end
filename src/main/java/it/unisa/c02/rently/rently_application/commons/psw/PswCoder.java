package it.unisa.c02.rently.rently_application.commons.psw;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

/**
 * Implementazione del servizio di codfica della password attraverso l'algoritmo di hashing SHA-256.
 * Questa classe fornisce servizi a più sottosistemi.
 */
@Component
public class PswCoder {


    /**
     * Costruttore senza argomenti.
     */
    public PswCoder() {
    }


    /**
     * Codifica una password tramite l'algoritmo di hashing SHA-256.
     *
     * @param password password da codificare
     * @return il risultato della chiamata alla funzione bytesToHex
     */
    public String codificaPassword (String password) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        return bytesToHex(encodedhash);
    }


    /**
     * Converte un array di byte in una stringa in esadecimale.
     *
     * @param hash array di byte devivante dalla password codificata
     * @return la stringa in esadecimale
     */
    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
