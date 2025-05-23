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

/*
 * Classe portefeuille
 */
public class Portfeuille {

    List<Action> actions;

    /**
     * Constructeur, construit un portefeuille vide
     */
    public Portfeuille() {
        this.actions = new ArrayList<>();
    }

    /**
     * donne les actions du portfeuille
     *
     * @return actions
     */
    public List<Action> getActions() {
        return actions;
    }

    /**
     * change les actions du portefeuille (setter) throw NullPointerException
     *
     * @param actions List<Action>
     */
    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    /**
     * Ajoute une action throw NullPointerException si action est null
     *
     * @param action
     */
    public void ajoutAction(ActionSimple action) {
        if (action == null) {
            throw new NullPointerException();
        }
        this.actions.add(action);
    }

}
