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

/**
 * Cette classe représente un client qui possède un portefeuille d'actions.
 * Chaque client est identifié par un identifiant unique, un nom et un prénom.
 * Les clients peuvent acheter des actions et gérer leur portefeuille.
 */
public class Client {

    /** Compteur statique pour générer des identifiants uniques */
    private static int lastID = 0;

    /** Le portefeuille d'actions du client */
    private Portfeuille portfeuille;
    
    /** Le nom de famille du client */
    private String nom;
    
    /** Le prénom du client */
    private String prenom;
    
    /** L'identifiant unique du client, non modifiable après création */
    private final int id;

    /**
     * Constructeur pour créer un nouveau client.
     * Génère automatiquement un identifiant unique pour le client.
     *
     * @param nom Le nom de famille du client
     * @param portfeuille Le portefeuille d'actions du client
     * @param prenom Le prénom du client
     */
    public Client(String nom, Portfeuille portfeuille, String prenom) {
        this.nom = nom;
        this.portfeuille = portfeuille;
        this.prenom = prenom;
        this.id = lastID + 1;

        augmenteID();
    }

    /**
     * Récupère le portefeuille du client.
     *
     * @return Le portefeuille du client
     */
    public Portfeuille getPortfeuille() {
        return portfeuille;
    }

    /**
     * Modifie le portefeuille du client.
     *
     * @param portfeuille Le nouveau portefeuille à attribuer au client
     */
    public void setPortfeuille(Portfeuille portfeuille) {
        this.portfeuille = portfeuille;
    }

    /**
     * Récupère le nom de famille du client.
     *
     * @return Le nom de famille du client
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom de famille du client.
     *
     * @param nom Le nouveau nom de famille du client
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le prénom du client.
     *
     * @return Le prénom du client
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifie le prénom du client.
     *
     * @param prenom Le nouveau prénom du client
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Récupère l'identifiant unique du client.
     *
     * @return L'identifiant unique du client
     */
    public int getId() {
        return id;
    }

    /**
     * Permet au client d'acheter une action simple et l'ajoute à son portefeuille.
     * 
     * @param action L'action simple à acheter
     * @throws NullPointerException si l'action est null
     */
    public void acheterActionSimple(ActionSimple action) {
        if (action == null) {
            throw new NullPointerException();
        }
        this.portfeuille.ajoutAction(action);
    }

    /**
     * Permet au client d'acheter plusieurs actions simples et les ajoute à son portefeuille.
     * 
     * @param actions La liste des actions simples à acheter
     * @throws NullPointerException si une des actions de la liste est null
     */
    public void acheterPlusieursActionsSimple(List<ActionSimple> actions) {
        nonNullActionInList(actions);
        
        for (ActionSimple actionSimple : actions) {
            this.acheterActionSimple(actionSimple);
        }
    }

    /**
     * Supprime le portefeuille du client s'il est vide.
     * 
     * @return true si le portefeuille a été supprimé, false sinon
     */
    public boolean suppressionPortfeuille() {
        if (this.portfeuille != null && this.portefeuilleVide()) {
            this.portfeuille = null;
            return true;
        }
        return false;
    }

    /**
     * Vérifie si le portefeuille du client est vide et le supprime si c'est le cas.
     * 
     * @return true si le portefeuille est vide, false sinon
     */
    private boolean portefeuilleVide() {
        if (this.portfeuille.getActions().isEmpty()) {
            this.portfeuille = null;
            return true;
        }
        return false;
    }

    /**
     * Vérifie qu'aucune action dans la liste n'est null.
     * 
     * @param actions La liste d'actions à vérifier
     * @throws NullPointerException si une action est null
     */
    private void nonNullActionInList(List<ActionSimple> actions) {
        for (ActionSimple actionSimple : actions) {
            if (actionSimple == null) {
                throw new NullPointerException();
            }
        }
    }

    /**
     * Incrémente le compteur d'identifiants utilisé pour générer des IDs uniques.
     */
    private static void augmenteID() {
        lastID = lastID + 1;
    }
}