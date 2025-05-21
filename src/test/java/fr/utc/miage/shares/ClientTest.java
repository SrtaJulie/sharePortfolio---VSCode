/*
 * Copyright 2025 Team Dev ABCEJOY;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package fr.utc.miage.shares;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ClientTest {

    public static final String PRENOM = "jean";
    public static final String NOM = "michel";
    public static final String NOM_MODIFIE = "nom_modifié";
    public static final String PRENOM_MODIFIE = "prenom_modifié";

    @Test
    void testCheckClientID() {
        Client clientRef = new Client(NOM, new Portfeuille(), PRENOM);
        int baseId = clientRef.getId();
        Client c1 = new Client(NOM, new Portfeuille(), PRENOM);
        Client c2 = new Client(NOM, new Portfeuille(), PRENOM);

        assertEquals(baseId + 1, c1.getId());
        assertEquals(baseId + 2, c2.getId());
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

    @Test
    void testSetNomEtPrenom() {
        Client c = new Client(NOM, new Portfeuille(), PRENOM);
        c.setNom(NOM_MODIFIE);
        c.setPrenom(PRENOM_MODIFIE);
        assertAll("Test set nom et prenom",
                () -> {
                    String result = c.getNom();
                    assertEquals(NOM_MODIFIE, result);
                },
                () -> {
                    String result = c.getPrenom();
                    assertEquals(PRENOM_MODIFIE, result);
                }
        );
    }

    @Test
    void testGetPortefeuille() {
        Portfeuille p = new Portfeuille();
        Client c = new Client(NOM, p, PRENOM);
        Portfeuille result = c.getPortfeuille();

        assertEquals(p, result);
    }

    @Test
    void testSetPortefeuille() {
        Client c = new Client(NOM, new Portfeuille(), PRENOM);

        Portfeuille p = new Portfeuille();

        //Ajouter des actions aux portefeuille
        p.setActions(List.of(new ActionSimple("Action simple", null)));
        c.setPortfeuille(p);

        Portfeuille result = c.getPortfeuille();

        assertEquals(p, result);
    }

    @Test
    void testSupprimerPortefeuilleVide() {
        Client c = new Client(NOM, new Portfeuille(), PRENOM);

        assertEquals(true,c.suppressionPortfeuille());
    }

    @Test
    void testSupprimerUnPortefeuilleNonVide() {
        Client c = new Client(NOM, new Portfeuille(), PRENOM);

        Portfeuille p = new Portfeuille();

        //Ajouter des actions aux portefeuille
        p.setActions(List.of(new ActionSimple("Action simple", null)));
        c.setPortfeuille(p);

        assertEquals(false,c.suppressionPortfeuille());
    }

    @Test
    void testSupprimerUnPortefeuilleNull() {
        Client c = new Client(NOM, null, PRENOM);

        assertEquals(false,c.suppressionPortfeuille());
    }

}
