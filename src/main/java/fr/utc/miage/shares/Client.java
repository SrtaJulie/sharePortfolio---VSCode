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

import java.util.List;

public class Client {

    private static int lastID = 0;

    private Portfeuille portfeuille;
    private String nom;
    private String prenom;
    private final int id;

    public Client(String nom, Portfeuille portfeuille, String prenom) {
        this.nom = nom;
        this.portfeuille = portfeuille;
        this.prenom = prenom;
        this.id = lastID + 1;

        augmenteID();
    }

    public Portfeuille getPortfeuille() {
        return portfeuille;
    }

    public void setPortfeuille(Portfeuille portfeuille) {
        this.portfeuille = portfeuille;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void acheterActionSimple(ActionSimple action) {
        if (action == null) {
            throw new NullPointerException();
        }
        this.portfeuille.ajoutAction(action);
    }

    public void acheterPlusieursActionsSimple(List<ActionSimple> actions) {
        nonNullActionInList(actions);
        
        for (ActionSimple actionSimple : actions) {
            this.acheterActionSimple(actionSimple);
        }
    }

    public boolean suppressionPortfeuille() {
        if (this.portfeuille != null && this.portefeuilleVide()) {
            this.portfeuille = null;
            return true;
        }
        return false;
    }

    private boolean portefeuilleVide() {
        if (this.portfeuille.getActions().isEmpty()) {
            this.portfeuille = null;
            return true;
        }
        return false;
    }

    private void nonNullActionInList(List<ActionSimple> actions) {
        for (ActionSimple actionSimple : actions) {
            if (actionSimple == null) {
                throw new NullPointerException();
            }
        }
    }

    private static void augmenteID() {
        lastID = lastID + 1;
    }
}
