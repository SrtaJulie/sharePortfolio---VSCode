package fr.utc.miage.shares;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class PortfeuilleTest {

    @Test
    void testNewPortfeuille(){
        assertDoesNotThrow(() -> {
            Portfeuille p = new Portfeuille();
        });
    }

    @Test
    void testNewPortfeuilleIsEmpty(){
        Portfeuille p = new Portfeuille();

        List<Action> result = p.getActions();

        ArrayList<Action> expected = new ArrayList<>();

        assertEquals(expected, result);
    }

}
