/*
 * Copyright 2025 Team Dev ABCEJOY;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

 package fr.utc.miage.shares;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ActionSimpleTest {

    private static final double VALEUR_MINIMAL_COURS = 0.01;
    private static final String LIBELLE_ACTION_SIMPLE = "tagada";
    private static final String LIBELLE_ACTION_SIMPLE2 = "fraise";
    private static final Jour JOUR_COUR = new Jour(2025,4);
    private static final Jour JOUR_COUR2 = new Jour(2026,6);
    private static final HashMap<Jour, Double> COUR = new HashMap<>();
    final ActionSimple actions= new ActionSimple(LIBELLE_ACTION_SIMPLE, COUR);
    
    @Test
    void testValeurCoursInferieur0ShouldTrhowExcepton() {
        assertThrows(IllegalArgumentException.class, () -> actions.ajouterCours(JOUR_COUR, -1.0));
    }

    @Test
    void testValeurDuCoursEgalA0ShouldTrhowException(){
       assertThrows(IllegalArgumentException.class, () -> actions.ajouterCours(JOUR_COUR, 0.0));
    }

    @Test
    void testCourValueSup0ShouldNotThrhowException(){
            actions.ajouterCours(JOUR_COUR, VALEUR_MINIMAL_COURS); // Ne doit pas lever d'exception
            
            //On verifie que la valeur a bien été ajoutée
            assertEquals(VALEUR_MINIMAL_COURS, actions.valeurActionSimpleDateDonnee(JOUR_COUR));
    }

    @Test
    void testLibelleIdentique(){
        Assertions.assertEquals(LIBELLE_ACTION_SIMPLE, actions.getLibelle());
    }

    @Test
    void testDefaultActionValue() {
        assertEquals(0.01, ActionSimple.getDefaultActionValue(), 0.0);
    }

    @Test
    void testConstructorWithCorrectParametersShouldWork() {
        Assertions.assertDoesNotThrow(()->{
            new ActionSimple(LIBELLE_ACTION_SIMPLE, COUR);
        });
    }

    @Test
    void testJourDeMapCours() {
        ActionSimple action = new ActionSimple("Test Action", COUR);

        action.getMapCours().put(new Jour(2025, 1), 100.0);
        action.getMapCours().put(new Jour(2025, 2), 105.0);

        assertEquals(2, action.getMapCours().size(), "La taille de la mapCours devrait être 2");
        assertTrue(action.getMapCours().containsKey(new Jour(2025, 1)), "La mapCours devrait contenir la clé Jour(2025, 1)");
        assertTrue(action.getMapCours().containsKey(new Jour(2025, 2)), "La mapCours devrait contenir la clé Jour(2025, 2)");
        assertFalse(action.getMapCours().containsKey(new Jour(2025, 3)), "La mapCours ne devrait pas contenir la clé Jour(2025, 3)");
        assertEquals(100.0, action.getMapCours().get(new Jour(2025, 1)), "La valeur associée à Jour(2025, 1) devrait être 100.0");
        assertEquals(105.0, action.getMapCours().get(new Jour(2025, 2)), "La valeur associée à Jour(2025, 2) devrait être 105.0");
    }
    
    @Test
    void testValeurActionJourDonnee() {
        actions.ajouterCours(JOUR_COUR, 2);

        Assertions.assertEquals(2, actions.valeurActionSimpleDateDonnee(JOUR_COUR));
    }

    @Test
    void TestLibelleActionUpToDate(){
         // Initialisation de l'objet à tester
        ActionSimple action = new ActionSimple(LIBELLE_ACTION_SIMPLE, COUR);

        // Données de test
        String nouveauLibelle = "Nouvelle Action";
        Map<Jour, Double> nouveauCour = new HashMap<>();
        nouveauCour.put(JOUR_COUR2, 100.0);

        // Appel de la méthode à tester
        action.modifierActionSimple(nouveauLibelle, nouveauCour);

        // Vérification que les champs ont bien été modifiés
        assertEquals(nouveauLibelle, action.getLibelle());
        assertEquals(nouveauCour, action.getCour());
    }

    @Test
    void testModificationLibelleVideThrowsException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            actions.modifierActionSimple("", COUR);
        });
    }

    @Test
    void testModificationLibelleNullThrowsException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            actions.modifierActionSimple(null, COUR);
        });
    }

    @Test
    void testModificationCoursVideThrowsException(){
        HashMap<Jour, Double> map = new HashMap<>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            actions.modifierActionSimple("Action Valide", map);
        });
    }

    @Test
    void testModificationCoursNullThrowsException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            actions.modifierActionSimple("Action Valide", null);
        });
    }
}
