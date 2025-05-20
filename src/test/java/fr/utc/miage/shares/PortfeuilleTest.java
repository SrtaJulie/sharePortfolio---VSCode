package fr.utc.miage.shares;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

class PortfeuilleTest {

    @Test
    void testNewEmptyPortfeuille(){
        assertDoesNotThrow(() -> {
            Portfeuille p = new Portfeuille();
        });
    }

}
