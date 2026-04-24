package tp5;

public abstract class Abonnement {
    private String nom;
    private double prixBase;
    private int nbProfils;

    public Abonnement(String nom, double prixBase, int nbProfils) {
        this.nom = nom;
        // Si le prix de base est inférieur ou égal à zéro, valeur par défaut (ex: 50.0)
        this.prixBase = (prixBase <= 0) ? 50.0 : prixBase; 
        // Si le nombre de profils est inférieur ou égal à zéro, la valeur 1 doit être utilisée
        this.nbProfils = (nbProfils <= 0) ? 1 : nbProfils;
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getPrixBase() { return prixBase; }
    public void setPrixBase(double prixBase) { 
        this.prixBase = (prixBase <= 0) ? 50.0 : prixBase; 
    }

    public int getNbProfils() { return nbProfils; }
    public void setNbProfils(int nbProfils) { 
        this.nbProfils = (nbProfils <= 0) ? 1 : nbProfils; 
    }

    public boolean estPartageFamilial() {
        return nbProfils >= 3;
    }

    public void afficherInfos() {
        System.out.println("Nom de l'abonnement: " + nom);
        System.out.println("Prix de base: " + prixBase + " DH");
        System.out.println("Nombre de profils: " + nbProfils);
        System.out.println("Partage Familial: " + (estPartageFamilial() ? "Oui" : "Non"));
    }

    public abstract double calculerCoutMensuel();
    public abstract int calculerScoreSatisfaction();
}
