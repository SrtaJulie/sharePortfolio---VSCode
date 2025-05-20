public class ActionComposee {
    /*
 * Copyright 2025 David Navarre &lt;David.Navarre at irit.fr&gt;.
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

import java.util.HashMap;
import java.util.Map;

/**
 * Allows the creation of simple Action objects.
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
public class ActionComposee extends Action {

    private static final int DEFAULT_ACTION_VALUE = 0;

    // attribut lien
    private Map<Jour, Float> mapCours;
    private Map<String, Float> composition;

    // constructeur
    public Action(final String libelle) {
        // Action simple initialisée comme 1 action
        super(libelle);
        // init spécifique
        this.mapCours = new HashMap<>();
        this.composition = new HashMap<>();
    }

    //Getter and Setter
    public Map<Jour, Float> getMapCours() {
        return mapCours;
    }

    public void setMapCours(Map<Jour, Float> mapCours) {
        this.mapCours = mapCours;
    }
    public Map<String, Float> getComposition() {
        return composition;
    }
    public void setComposition(Map<String, Float> composition) {
        this.composition = composition;
    }
    
    // enrg possible si pas de cours pour ce jour
    public void enrgCours(final Jour j, final float v) {
        if (!this.mapCours.containsKey(j)) {
            this.mapCours.put(j, v);
        }
    }

    //Enregistrer pour chaque 

    @Override
    public float valeur(final Jour j) {
        if (this.mapCours.containsKey(j)) {
            return this.mapCours.get(j);
        } else {
            return DEFAULT_ACTION_VALUE;
        }
    }

    
}

}
