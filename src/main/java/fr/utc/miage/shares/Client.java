package fr.utc.miage.shares;

public class Client {
    private static int lastID = 0;

    public static int getLastID() {
        return lastID;
    }

    private Portfeuille portfeuille;
    private String nom;
    private String prenom;
    private final int id;

    public Client(String nom, Portfeuille portfeuille, String prenom) {
        this.nom = nom;
        this.portfeuille = portfeuille;
        this.prenom = prenom;
        this.id = lastID +1;
        lastID = lastID + 1;
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

}
