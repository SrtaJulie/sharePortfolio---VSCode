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
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;


public class ActionComposeeTest {

    @Test
    void testCreateAndDisplayActionComposee() {
        // Create Jour
        Jour jour1 = new Jour(2023, 2);
        Jour jour2 = new Jour(2023, 12);

        // Create cours for each ActionSimple
        HashMap<Jour, Double> cour1 = new HashMap<>();
        cour1.put(jour1, (double) 100.0f);

        HashMap<Jour, Double> cour2 = new HashMap<>();
        cour2.put(jour2, (double) 200.0f);

        // Create ActionSimple
        ActionSimple action1 = new ActionSimple("Action A", cour1);
        ActionSimple action2 = new ActionSimple("Action B", cour2);

        // Compose ActionComposee
        Map<ActionSimple, Float> composition = new HashMap<>();
        composition.put(action1, 40.0f);
        composition.put(action2, 40.0f);

        ActionComposee actionComposee = new ActionComposee(composition);

        System.out.println("Composition : " + actionComposee.getComposition());

        Map<ActionSimple, Float> expected = new HashMap<>();
        expected.put(action1, 40.0f);
        expected.put(action2, 40.0f);

        assertEquals(expected, actionComposee.getComposition());
    }
}