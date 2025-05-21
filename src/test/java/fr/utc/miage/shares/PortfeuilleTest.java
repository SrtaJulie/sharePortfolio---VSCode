/*
 * Copyright 2025 Team Dev ABCEJOY;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.utc.miage.shares;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class PortfeuilleTest {

    private static final String ACTION_LIBELLE = "actionLibelle";

    @Test
    void testNewPortfeuille(){
        assertDoesNotThrow(Portfeuille::new);
    }

    @Test
    void testNewPortfeuilleIsEmpty(){
        Portfeuille p = new Portfeuille();

        List<Action> result = p.getActions();

        ArrayList<Action> expected = new ArrayList<>();

        assertEquals(expected, result);
    }

    @Test
    void testPortefeuilleSetter(){
        Portfeuille p = new Portfeuille();
        Action action = new ActionSimple(ACTION_LIBELLE, null);

        ArrayList<Action> expected = new ArrayList<>();

        expected.add(action);

        p.setActions(expected);

        List<Action> result = p.getActions();

        assertEquals(expected, result);
    }

}
