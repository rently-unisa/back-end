package it.unisa.c02.rently.rently_application.commons.services.regexService;

import java.util.HashMap;

public class RegexTester {

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