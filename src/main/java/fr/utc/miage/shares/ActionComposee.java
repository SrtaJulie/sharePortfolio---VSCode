package fr.utc.miage.shares;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe représentant une action composée, constituée de plusieurs ActionSimple
 * avec un pourcentage associé.
 */
public class ActionComposee extends Action {

    // Map associant une ActionSimple à son pourcentage dans la composition
    private final Map<ActionSimple, Float> composition;

    /**
     * Constructeur : crée une ActionComposee à partir d'une composition.
     *
     * @param libelle     Libellé de l'action composée
     * @param composition Map des actions simples et leur pourcentage
     * @param cours       Map des cours (Jour -> valeur)
     * @throws IllegalArgumentException si la composition est nulle ou contient moins de 2 actions
     */
    public ActionComposee(String libelle, Map<ActionSimple, Float> composition, Map<Jour, Double> cours) {
        super(libelle, new HashMap<>(cours)); // Conversion explicite en HashMap
        if (composition == null || composition.size() < 2) {
            throw new IllegalArgumentException("Une action composée doit contenir au moins deux actions simples.");
        }
        this.composition = new HashMap<>(composition);
    }

    /**
     * Ajoute une ActionSimple à la composition.
     *
     * @param actionSimple L'action à ajouter
     * @param pourcentage Le pourcentage associé
     * @throws IllegalArgumentException si le pourcentage est invalide ou si le
     * total dépasse 100
     */
    public void ajoutAction(ActionSimple actionSimple, float pourcentage) {
        if (actionSimple == null) {
            throw new IllegalArgumentException("L'action ne peut pas être nulle.");
        }
        if (pourcentage <= 0.0f) {
            throw new IllegalArgumentException("Le pourcentage doit être strictement positif.");
        }
        float total = getPourcentageTot() + pourcentage;
        if (total > 100.0f) {
            throw new IllegalArgumentException("Le pourcentage total ne doit pas dépasser 100.");
        }
        composition.put(actionSimple, pourcentage);
    }

    /**
     * Calcule le pourcentage total de la composition.
     *
     * @return la somme des pourcentages
     */
    public float getPourcentageTot() {
        float total = 0.0f;
        for (float pourcentage : composition.values()) {
            total += pourcentage;
        }
        return total;
    }

    /**
     * Retire une ActionSimple de la composition.
     *
     * @param actionSimple L'action à retirer
     */
    public void retirerAction(ActionSimple actionSimple) {
        composition.remove(actionSimple);
    }

    /**
     * Récupère le pourcentage d'une ActionSimple dans la composition.
     *
     * @param actionSimple L'action recherchée
     * @return Le pourcentage associé, ou null si absente
     */
    public Float getPourcentage(ActionSimple actionSimple) {
        return composition.get(actionSimple);
    }

    /**
     * Retourne une map des libellés des actions simples et leur pourcentage.
     *
     * @return Map<String, Float> (libellé -> pourcentage)
     */
    public Map<String, Float> getComposition() {
        Map<String, Float> result = new HashMap<>();
        for (Map.Entry<ActionSimple, Float> entry : composition.entrySet()) {
            result.put(entry.getKey().getLibelle(), entry.getValue());
        }
        return result;
    }

    /**
     * Ajoute un cours pour un jour donné.
     * @param jour   Le jour pour lequel le cours est ajouté
     * @param valeur La valeur du cours 
     */
   public void ajouterCours(Jour jour, double valeur) {
        if (jour == null) {
            throw new IllegalArgumentException("Le jour ne peut pas être nul.");
        }
        if (valeur <= 0.0) {
            throw new IllegalArgumentException("La valeur du cours doit être strictement positive.");
        }
        super.getMapCours().put(jour, valeur); // Utilise getMapCours()
    }

    /**
     * Retourne la map des cours (Jour -> valeur).
     * @return Map des cours
     */
    

    public Map<Jour, Double> getCours() {
        return new HashMap<>(super.getMapCours());
    }
}
