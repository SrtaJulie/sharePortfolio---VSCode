package fr.utc.miage.shares;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David Navarre <David.Navarre at irit.fr>
 */
public class ActionComposee {

    //Map associant une Action simple à un pourcentage
    private Map<ActionSimple, Float> composition;

    public ActionComposee() {
        this.composition = new HashMap<>();
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
        float total = getTotalPercentage() + pourcentage;
        if (total > 100.0f) {
            throw new IllegalArgumentException("Le pourcentage total ne doit pas dépasser 100.");
        }
        composition.put(actionSimple, pourcentage);
    }

}