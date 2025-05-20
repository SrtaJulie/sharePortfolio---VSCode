package fr.utc.miage.shares;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ActionSimpleTest {

    private static final double VALEUR_MINIMAL_COURS = 0.01;
    private static final String LIBELLE_ACTION_SIMPLE = "tagada";
    final ActionSimple actions= new ActionSimple(LIBELLE_ACTION_SIMPLE);
    
    @Test
    void testValeurCoursSup0() {
        
    }

    @Test
    void testValeurDuCoursPasEgalA0(){
       
    }

    @Test
    void testLibelleIdentique(){
        Assertions.assertEquals(actions.getLibelle(), LIBELLE_ACTION_SIMPLE);
    }

    @Test
    void testDefaultActionValue() {
        assertEquals(0.01, ActionSimple.getDefaultActionValue(), 0.0);
    }

}
