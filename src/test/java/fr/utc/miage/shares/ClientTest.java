package fr.utc.miage.shares;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {

    public static final String PRENOM = "jean";
    public static final String NOM = "michel";

    @Test
    void testCheckClientID() {
        Client c1 = new Client(NOM, new Portfeuille(), PRENOM);
        Client c2 = new Client(NOM, new Portfeuille(), PRENOM);

       assertEquals(1, c1.getId());
       assertEquals(2, c2.getId());
    }

    @Test
    void testCheckClientNomEtPrenom() {
        Client c = new Client(NOM, new Portfeuille(), PRENOM);
        assertAll("Test nom et prenom",
            () -> {
                String result = c.getNom();
                assertEquals(NOM, result);
            },
            () -> {
                String result = c.getPrenom();
                assertEquals(PRENOM, result);
            }
        );
    }

    
}

