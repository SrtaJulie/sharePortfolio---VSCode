package fr.utc.miage.shares;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe représentant une action composée, constituée de plusieurs ActionSimple avec un pourcentage associé.
 */
public class ActionComposee {

    // Map associant une ActionSimple à son pourcentage dans la composition
    private Map<ActionSimple, Float> composition;

    /**
     * Constructeur : crée une ActionComposee à partir d'une composition.
     * @param composition Map des actions simples et leur pourcentage
     * @throws IllegalArgumentException si la map est nulle ou contient moins de 2 actions
     */
    public ActionComposee(Map<ActionSimple, Float> composition) {
        if (composition == null || composition.size() < 2) {
            throw new IllegalArgumentException("Une action composée doit contenir au moins deux actions simples.");
        }
        this.composition = new HashMap<>(composition);
    }

    /**
     * Ajoute une ActionSimple à la composition.
     * @param actionSimple L'action à ajouter
     * @param pourcentage Le pourcentage associé
     * @throws IllegalArgumentException si le pourcentage est invalide ou si le total dépasse 100
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

    /**
     * Calcule le pourcentage total de la composition.
     * @return la somme des pourcentages
     */
    public float getPourcentageTot() {
        float total = 0.0f;
        for (Float value : composition.values()) {
            total += value;
        }
        return total;
    }

    /**
     * Retire une ActionSimple de la composition.
     * @param actionSimple L'action à retirer
     */
    public void retirerAction(ActionSimple actionSimple) {
        composition.remove(actionSimple);
    }

    /**
     * Récupère le pourcentage d'une ActionSimple dans la composition.
     * @param actionSimple L'action recherchée
     * @return Le pourcentage associé, ou null si absente
     */
    public Float getPourcentage(ActionSimple actionSimple) {
        return composition.get(actionSimple);
    }

    /**
     * Retourne une map des libellés des actions simples et leur pourcentage.
     * @return Map<String, Float> (libellé -> pourcentage)
     */
    public Map<String, Float> getComposition() {
        Map<String, Float> result = new HashMap<>();
        for (Map.Entry<ActionSimple, Float> entry : composition.entrySet()) {
            result.put(entry.getKey().getLibelle(), entry.getValue());
        }
        return result;
    }
}