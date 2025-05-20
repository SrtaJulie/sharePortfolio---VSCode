package fr.utc.miage.shares;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ActionSimpleTest {

    private static final double VALEUR_MINIMAL_COURS = 0.01;
    private static final String LIBELLE_ACTION_SIMPLE = "tagada";
    final ActionSimple actions= new ActionSimple(LIBELLE_ACTION_SIMPLE);
    
    // @Test
    // void testValeurCoursSup0() {
        
    // }

    // @Test
    // void testValeurDuCoursPasEgalA0(){
       
    // }

    @Test
    void testLibelleIdentique(){
        Assertions.assertEquals(actions.getLibelle(), LIBELLE_ACTION_SIMPLE);
    }

    @Test
    void testDefaultActionValue() {
        assertEquals(0.01, ActionSimple.getDefaultActionValue(), 0.0);
    }

    @Test
    void testConstructorWithCorrectParametersShouldWork() {
        Assertions.assertDoesNotThrow(()->{
            new ActionSimple(LIBELLE_ACTION_SIMPLE);
        });
    }

    @Test
    void testMapCours() {
        ActionSimple action = new ActionSimple("Test Action");

        action.getMapCours().put(new Jour(2025, 1), 100.0);
        action.getMapCours().put(new Jour(2025, 2), 105.0);

        assertEquals(2, action.getMapCours().size(), "La taille de la mapCours devrait être 2");
        assertTrue(action.getMapCours().containsKey(new Jour(2025, 1)), "La mapCours devrait contenir la clé Jour(2025, 1)");
        assertTrue(action.getMapCours().containsKey(new Jour(2025, 2)), "La mapCours devrait contenir la clé Jour(2025, 2)");
        assertFalse(action.getMapCours().containsKey(new Jour(2025, 3)), "La mapCours ne devrait pas contenir la clé Jour(2025, 3)");
        assertEquals(100.0, action.getMapCours().get(new Jour(2025, 1)), "La valeur associée à Jour(2025, 1) devrait être 100.0");
        assertEquals(105.0, action.getMapCours().get(new Jour(2025, 2)), "La valeur associée à Jour(2025, 2) devrait être 105.0");
    }
    
}
