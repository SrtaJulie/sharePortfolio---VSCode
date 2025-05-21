/**
 * Copyright 2025 Team Dev ABCEJOY.


Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at


     http://www.apache.org/licenses/LICENSE-2.0


Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package fr.utc.miage.shares;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David Navarre <David.Navarre at irit.fr>
 */
public class ActionComposee {

    //Map associant une Action simple à un pourcentage
    private Map<ActionSimple, Float> composition;

public ActionComposee(Map<ActionSimple, Float> composition) {
    if (composition == null || composition.size() < 2) {
        throw new IllegalArgumentException("Une action composée doit contenir au moins deux actions simples.");
    }
    this.composition = new HashMap<>(composition);
}
    /**
     * Ajouter une ActionSimple à la composition.
     * @param actionSimple 
     * @param pourcentage le pourcentage de l'ActionSimple dans la composition
     * @throws IllegalArgumentException //Si pourcentage invalide ou si le total dépasse 100
     */
    public void ajoutAction(ActionSimple actionSimple, float pourcentage) {
        if (pourcentage <= 0) {
            throw new IllegalArgumentException("La valeur du pourcentage doit être supérieure à 0.");
        }
        float total = getPourcentageTot() + pourcentage;
        if (total > 100.0f) {
            throw new IllegalArgumentException("Le pourcentage total ne doit pas dépasser 100.");
        }
        composition.put(actionSimple, pourcentage);
    }

    public float getPourcentageTot() {
        float total = 0.0f;
        for (Float value : composition.values()) {
            total += value;
        }
        return total;
    }
        /**
     * Retirer une ActionSimple de la composition.
     * @param actionSimple 
     */
    public void retirerAction(ActionSimple actionSimple) {
        composition.remove(actionSimple);
    }
    /** 
     * Récupérer le pourcentage d'une ActionSimple dans la composition.
     * @param actionSimple 
     * @return 
     */
    public Float getPourcentage(ActionSimple actionSimple) {
        return composition.get(actionSimple);
    }
    public Map<ActionSimple, Float> getComposition() {
        return composition;
    }
}

