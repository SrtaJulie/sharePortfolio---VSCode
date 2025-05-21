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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class ActionComposeeTest {

    private ActionSimple action1;
    private ActionSimple action2;
    private Map<ActionSimple, Float> validComposition;

    // Prépare deux actions simples et une composition valide avant chaque test
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

    // Vérifie la construction correcte et la récupération de la composition
    @Test
    void testValidConstructionAndGetComposition() {
        ActionComposee ac = new ActionComposee(validComposition);
        Map<String, Float> expected = new HashMap<>();
        expected.put("Action A", 60.0f);
        expected.put("Action B", 40.0f);
        assertEquals(expected, ac.getComposition());
    }

    // Vérifie qu'une exception est levée si on essaie de créer une ActionComposee avec moins de 2 actions
    @Test
    void testConstructeurA() {
        Map<ActionSimple, Float> invalid = new HashMap<>();
        invalid.put(action1, 100.0f);
        assertThrows(IllegalArgumentException.class, () -> new ActionComposee(invalid));
    }

    // Vérifie qu'une exception est levée si la composition passée au constructeur est nulle
    @Test
    void testConstructeurB() {
        assertThrows(IllegalArgumentException.class, () -> new ActionComposee(null));
    }

    // Vérifie que getComposition retourne une copie et non la map interne (protection contre modification externe)
    @Test
    void testComposition() {
        ActionComposee ac = new ActionComposee(validComposition);
        Map<String, Float> comp1 = ac.getComposition();
        comp1.put("Fake", 10.0f);
        assertFalse(ac.getComposition().containsKey("Fake"));
    }
}