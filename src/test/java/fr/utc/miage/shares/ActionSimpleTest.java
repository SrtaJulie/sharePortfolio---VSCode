package fr.utc.miage.shares;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActionSimpleTest {

    private static final double VALEUR_MINIMAL_COURS = 0;
    private static final String LIBELLE_ACTION_SIMPLE = "tagada";
    @Test
    void testValeurCoursSup0() {
        final ActionSimple actions= new ActionSimple(LIBELLE_ACTION_SIMPLE);
    }
}
