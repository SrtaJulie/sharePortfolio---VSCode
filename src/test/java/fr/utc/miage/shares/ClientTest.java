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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    public static final String PRENOM = "jean";
    public static final String NOM = "michel";
    public static final String NOM_MODIFIE = "nom_modifié";
    public static final String PRENOM_MODIFIE = "prenom_modifié";
    public static final String LIBELLE_ACT = "Action simple";
    public static final HashMap<Jour, Double> MAP_COURS = new HashMap<>();

    @Test
    void testCheckClientID() {
        Client clientRef = getClient();
        int baseId = clientRef.getId();
        Client c1 = getClient();
        Client c2 = getClient();

        assertEquals(baseId + 1, c1.getId());
        assertEquals(baseId + 2, c2.getId());
    }

    @Test
    void testCheckClientNomEtPrenom() {
        Client c = getClient();
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
        Client c = getClient();
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
        Client c = getClient();

        Portfeuille p = new Portfeuille();

        //Ajouter des actions aux portefeuille
        p.setActions(List.of(new ActionSimple("Action simple", null)));
        c.setPortfeuille(p);

        Portfeuille result = c.getPortfeuille();

        assertEquals(p, result);
    }

    @Test
    void testSupprimerPortefeuilleVide() {
        Client c = getClient();

        assertEquals(true,c.suppressionPortfeuille());
    }

    @Test
    void testSupprimerUnPortefeuilleNonVide() {
        Client c = getClient();

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

    @Test
    void testAcheterActionNull(){
        ActionSimple a = null;
        Client c = getClient();

        assertThrows(NullPointerException.class, () -> {
            c.acheterActionSimple(a);
        });
    }

    @Test
    void testAcheterPlusieursActionDontUneNull(){
        ActionSimple a = getActionSimple();
        List<ActionSimple> list = new ArrayList<>();
        list.add(a);
        list.add(null);
        list.add(a);
        Client c = getClient();
        assertThrows(NullPointerException.class, () -> {
            c.acheterPlusieursActionsSimple(list);
        });

        List<Action> rs = c.getPortfeuille().getActions();

        ArrayList<Action> expected = new ArrayList<>();
        assertEquals(expected, rs);
    }

    @Test
    void testSupprimerUnePortfeuilleNonVide(){
        ActionSimple a = getActionSimple();
        Client c = getClient();
        c.acheterActionSimple(a);
        boolean rs = c.suppressionPortfeuille();

        assertEquals(false, rs);
    }

    @Test
    void testAjouterUneActionsSimple(){
        ActionSimple a = getActionSimple();
        List<ActionSimple> list = new ArrayList<>();
        list.add(a);
        Client c = getClient();
        c.acheterActionSimple(a);
        List<Action> rs = c.getPortfeuille().getActions();

        assertEquals(list, rs);
    }

    @Test
    void testAjouterPlusieursActionsSimple(){
        ActionSimple a = getActionSimple();
        List<ActionSimple> list = new ArrayList<>();
        list.add(a);
        list.add(a);
        list.add(a);
        Client c = getClient();
        c.acheterPlusieursActionsSimple(list);
        List<Action> rs = c.getPortfeuille().getActions();

        assertEquals(list, rs);
    }

    private static ActionSimple getActionSimple() {
        MAP_COURS.put(new Jour(2025, 1), 100.0);
        return new ActionSimple(LIBELLE_ACT, MAP_COURS);
    }

    private static Client getClient() {
        return new Client(NOM, new Portfeuille(), PRENOM);
    }

}
