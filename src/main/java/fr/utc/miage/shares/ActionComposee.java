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

    /**
     * Retourne la composition totale de l'ActionComposee avec les libellés des actions.
     * @return 
     */
    public Map<String, Float> getComposition() {
        Map<String, Float> compositionLibelle = new HashMap<>();
        for (Map.Entry<ActionSimple, Float> entry : composition.entrySet()) {
            compositionLibelle.put(entry.getKey().getLibelle(), entry.getValue());
        }
        return compositionLibelle;
    }

}