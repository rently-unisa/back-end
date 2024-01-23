package it.unisa.c02.rently.rently_application.commons.services.regexService;

import java.util.HashMap;
/**
 * Questa classe implementa il servizio di controllo delle regex.
 *
 */
public class RegexTester {
    /**
     * Implementa la funzionalità di verifica che una stringa rispetti una regex.
     *
     * @param regex la regex da testare
     * @return true se la rispetta, false altrimenti
     */
    public boolean toTest(final HashMap<String, String> regex) {

        return regex.entrySet().stream().allMatch(entry -> {

                    if (entry.getKey() == null) {
                        return true;
                    }
                    return entry.getKey().matches(entry.getValue());
                }
        );
    }
}