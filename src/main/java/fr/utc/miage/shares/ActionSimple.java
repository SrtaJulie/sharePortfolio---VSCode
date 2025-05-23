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
public class ActionSimple extends Action {

    /* Propriétées */
    private static final double DEFAULT_ACTION_VALUE = 0.01;
    private Map<Jour, Double> cour = new HashMap<>();

    /**
     * Constructeur
     * @param libelle le nom donnée à l'action
     * @param cour le tableau des cours de l'action
     */
    public ActionSimple(final String libelle, final HashMap<Jour, Double> cour) {
        // Action simple initialisée comme 1 action
        super(libelle, cour);
    }

    /**
     * 
     * @return la valeur par défaut de l'action en double
     */
    public static double getDefaultActionValue() {
        return DEFAULT_ACTION_VALUE;
    }

    /**
     * Ajoute un cours au tableau de l'action
     * @param jour le jour du cours
     * @param valeur la valeur du cours
     */
    public void ajouterCours(Jour jour, double valeur) {
        if (valeur <= 0.0) {
            throw new IllegalArgumentException("La valeur du cours doit être strictement positive.");
        }
        cour.put(jour, valeur);
    }
    
    /**
     * Retour le libelle de l'action 
     */
    public String getLibelle() {
        return super.getLibelle();
    }

    /**
     * Recupère la valeur de l'action un jour donnée
     * @param jour le jour du cours
     * @return la valeur de l'action
     */
    public double valeurActionSimpleDateDonnee(Jour jour){
        double valeur = 0.0;

        valeur = this.cour.get(jour);
        return valeur;
    }

}
