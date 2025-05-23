package fr.utc.miage.shares;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class ActionComposeeTest {

    private ActionSimple action1;
    private ActionSimple action2;
    private Map<ActionSimple, Float> validComposition;

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
    }

    // Teste la construction correcte et la récupération de la composition
    @Test
    void testValidConstructionAndGetComposition() {
        ActionComposee ac = new ActionComposee(validComposition);
        Map<String, Float> expected = new HashMap<>();
        expected.put("Action A", 60.0f); // ✅ corrige ici
        expected.put("Action B", 40.0f);
        assertEquals(expected, ac.getComposition());
    }

    // Teste la récupération du pourcentage d'une action
    @Test
    void testGetPourcentage() {
        ActionComposee ac = new ActionComposee(validComposition);
        assertEquals(60.0f, ac.getPourcentage(action1));
        assertEquals(40.0f, ac.getPourcentage(action2));
        // Teste pour une action non présente
        ActionSimple fake = new ActionSimple("Fake", new HashMap<>());
        assertNull(ac.getPourcentage(fake));
    }

    // Teste le calcul du pourcentage total
    @Test
    void testGetPourcentageTot() {
        ActionComposee ac = new ActionComposee(validComposition);
        assertEquals(100.0f, ac.getPourcentageTot());
    }

    // Teste l'ajout d'une action valide
    @Test
    void testAjoutAction() {
        // Crée une composition initiale à 90%
        Map<ActionSimple, Float> partialComposition = new HashMap<>();
        partialComposition.put(action1, 60.0f);
        partialComposition.put(action2, 30.0f); // total = 90%

        ActionComposee ac = new ActionComposee(partialComposition);
        ActionSimple action3 = new ActionSimple("Action C", new HashMap<>());
        ac.ajoutAction(action3, 10f); // OK maintenant

        assertEquals(10f, ac.getPourcentage(action3));
    }

    // Teste l'ajout d'une action avec un pourcentage négatif ou nul
    @Test
    void testAjoutActionPourcentageInvalide() {
        ActionComposee ac = new ActionComposee(validComposition);
        ActionSimple action3 = new ActionSimple("Action C", new HashMap<>());
        assertThrows(IllegalArgumentException.class, () -> ac.ajoutAction(action3, 0.0f));
        assertThrows(IllegalArgumentException.class, () -> ac.ajoutAction(action3, -10.0f));
    }

    // Teste l'ajout d'une action qui ferait dépasser 100%
    @Test
    void testAjoutActionDepassement() {
        ActionComposee ac = new ActionComposee(validComposition);
        ActionSimple action3 = new ActionSimple("Action C", new HashMap<>());
        assertThrows(IllegalArgumentException.class, () -> ac.ajoutAction(action3, 50.0f));
    }

    // Teste le retrait d'une action
    @Test
    void testRetirerAction() {
        ActionComposee ac = new ActionComposee(validComposition);
        ac.retirerAction(action1);
        assertNull(ac.getPourcentage(action1));
        // On peut aussi vérifier que la composition ne contient plus l'action
        assertFalse(ac.getComposition().containsKey("Action A"));
    }

    // Teste la protection contre modification externe de la map retournée
    @Test
    void testCompositionReturnsCopy() {
        ActionComposee ac = new ActionComposee(validComposition);
        Map<String, Float> comp1 = ac.getComposition();
        comp1.put("Fake", 10.0f);
        assertFalse(ac.getComposition().containsKey("Fake"));
    }

    // Teste la construction avec moins de 2 actions
    @Test
    void testConstructeurAvecMoinsDeDeuxActions() {
        Map<ActionSimple, Float> invalid = new HashMap<>();
        invalid.put(action1, 100.0f);
        assertThrows(IllegalArgumentException.class, () -> new ActionComposee(invalid));
    }

    // Teste la construction avec une composition nulle
    @Test
    void testConstructeurAvecNull() {
        assertThrows(IllegalArgumentException.class, () -> new ActionComposee(null));
    }
}