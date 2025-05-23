package fr.utc.miage.shares;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ActionComposeeTest {

    private ActionSimple action1;
    private ActionSimple action2;
    private Map<ActionSimple, Float> validComposition;
    private Map<Jour, Double> cours;

    @BeforeEach
    void setup() {
        Jour jour1 = new Jour(2023, 2);
        Jour jour2 = new Jour(2023, 12);

        HashMap<Jour, Double> cour1 = new HashMap<>();
        cour1.put(jour1, 100.0);

        HashMap<Jour, Double> cour2 = new HashMap<>();
        cour2.put(jour2, 200.0);

        action1 = new ActionSimple("Action A", cour1);
        action2 = new ActionSimple("Action B", cour2);

        validComposition = new HashMap<>();
        validComposition.put(action1, 60.0f);
        validComposition.put(action2, 40.0f);

        cours = new HashMap<>();
        cours.put(jour1, 150.0);
    }

    @Test
    void testValidConstructionAndGetComposition() {
        ActionComposee ac = new ActionComposee("AC", validComposition, cours);
        Map<String, Float> expected = new HashMap<>();
        expected.put("Action A", 60.0f);
        expected.put("Action B", 40.0f);
        assertEquals(expected, ac.getComposition());
    }

    @Test
    void testGetPourcentage() {
        ActionComposee ac = new ActionComposee("AC", validComposition, cours);
        assertEquals(60.0f, ac.getPourcentage(action1));
        assertEquals(40.0f, ac.getPourcentage(action2));
        ActionSimple fake = new ActionSimple("Fake", new HashMap<>());
        assertNull(ac.getPourcentage(fake));
    }

    @Test
    void testGetPourcentageTot() {
        ActionComposee ac = new ActionComposee("AC", validComposition, cours);
        assertEquals(100.0f, ac.getPourcentageTot());
    }

    @Test
    void testAjoutAction() {
        Map<ActionSimple, Float> partialComposition = new HashMap<>();
        partialComposition.put(action1, 60.0f);
        partialComposition.put(action2, 30.0f);
        ActionComposee ac = new ActionComposee("AC", partialComposition, cours);
        ActionSimple action3 = new ActionSimple("Action C", new HashMap<>());
        ac.ajoutAction(action3, 10f);
        assertEquals(10f, ac.getPourcentage(action3));
    }

    @Test
    void testAjoutActionPourcentageInvalide() {
        ActionComposee ac = new ActionComposee("AC", validComposition, cours);
        ActionSimple action3 = new ActionSimple("Action C", new HashMap<>());
        assertThrows(IllegalArgumentException.class, () -> ac.ajoutAction(action3, 0.0f));
        assertThrows(IllegalArgumentException.class, () -> ac.ajoutAction(action3, -10.0f));
    }

    @Test
    void testAjoutActionDepassement() {
        ActionComposee ac = new ActionComposee("AC", validComposition, cours);
        ActionSimple action3 = new ActionSimple("Action C", new HashMap<>());
        assertThrows(IllegalArgumentException.class, () -> ac.ajoutAction(action3, 50.0f));
    }

    @Test
    void testRetirerAction() {
        ActionComposee ac = new ActionComposee("AC", validComposition, cours);
        ac.retirerAction(action1);
        assertNull(ac.getPourcentage(action1));
        assertFalse(ac.getComposition().containsKey("Action A"));
    }

    @Test
    void testCompositionReturnsCopy() {
        ActionComposee ac = new ActionComposee("AC", validComposition, cours);
        Map<String, Float> comp1 = ac.getComposition();
        comp1.put("Fake", 10.0f);
        assertFalse(ac.getComposition().containsKey("Fake"));
    }

    @Test
    void testConstructeurAvecMoinsDeDeuxActions() {
        Map<ActionSimple, Float> invalid = new HashMap<>();
        invalid.put(action1, 100.0f);
        assertThrows(IllegalArgumentException.class, () -> new ActionComposee("AC", invalid, cours));
    }

    @Test
    void testConstructeurAvecNull() {
        assertThrows(IllegalArgumentException.class, () -> new ActionComposee("AC", null, cours));
    }

    @Test
    void testAjouterCours() {
        ActionComposee ac = new ActionComposee("AC", validComposition, new HashMap<>());
        Jour jour1 = new Jour(2023, 2);
        double valeur = 123.45;
        ac.ajouterCours(jour1, valeur);
        assertEquals(valeur, ac.getCours().get(jour1), 0.001);
    }

    @Test
    void testGetCours() {
        ActionComposee ac = new ActionComposee("AC", validComposition, new HashMap<>());
        Jour jour1 = new Jour(2023, 2);
        Jour jour2 = new Jour(2024, 2);
        ac.ajouterCours(jour1, 100.0);
        ac.ajouterCours(jour2, 200.0);

        Map<Jour, Double> cours = ac.getCours();
        assertEquals(2, cours.size());
        assertEquals(100.0, cours.get(jour1), 0.001);
        assertEquals(200.0, cours.get(jour2), 0.001);
    }
}
