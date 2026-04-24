# TP 5 Java - Classe abstraite & Interface
## NOM : EL OMARI LAHCEN  
## Lien vers le projet : https://github.com/el7-assan/Java_et_Jee-TP_5.git

## 1. Classe abstraite `Abonnement.java`

```java
package tp5;

public abstract class Abonnement {
    private String nom;
    private double prixBase;
    private int nbProfils;

    public Abonnement(String nom, double prixBase, int nbProfils) {
        this.nom = nom;
        this.prixBase = (prixBase <= 0) ? 50.0 : prixBase; 
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
```

## 2. Interface `Reducible.java`

```java
package tp5;

public interface Reducible {
    double appliquerReduction(double pourcentage);
    boolean estEligibleReduction(double pourcentage);
}
```

## 3. Classe `AbonnementVideo.java`

```java
package tp5;

public class AbonnementVideo extends Abonnement implements Reducible {
    private boolean optionHD;
    private boolean option4K;

    public AbonnementVideo(String nom, double prixBase, int nbProfils, boolean optionHD, boolean option4K) {
        super(nom, prixBase, nbProfils);
        this.optionHD = optionHD;
        this.option4K = option4K;
    }

    public boolean isOptionHD() { return optionHD; }
    public void setOptionHD(boolean optionHD) { this.optionHD = optionHD; }
    
    public boolean isOption4K() { return option4K; }
    public void setOption4K(boolean option4K) { this.option4K = option4K; }

    @Override
    public double calculerCoutMensuel() {
        double cout = getPrixBase();
        if (optionHD) cout += 10.0;
        if (option4K) cout += 20.0;
        return cout;
    }

    @Override
    public int calculerScoreSatisfaction() {
        int score = 60; // Valeur initiale
        if (optionHD) score += 10;
        if (option4K) score += 20;
        if (getNbProfils() >= 4) score += 10;
        
        return Math.min(score, 100); // Ramené à 100 si dépassement
    }

    @Override
    public void afficherInfos() {
        System.out.println("--- Abonnement Vidéo ---");
        super.afficherInfos();
        System.out.println("Option HD: " + (optionHD ? "Activée" : "Désactivée"));
        System.out.println("Option 4K: " + (option4K ? "Activée" : "Désactivée"));
    }

    @Override
    public boolean estEligibleReduction(double pourcentage) {
        return pourcentage <= 30.0;
    }

    @Override
    public double appliquerReduction(double pourcentage) {
        if (estEligibleReduction(pourcentage)) {
            return calculerCoutMensuel() * (1 - pourcentage / 100.0);
        }
        return calculerCoutMensuel();
    }
}
```

## 4. Classe `AbonnementMusique.java`

```java
package tp5;

public class AbonnementMusique extends Abonnement implements Reducible {
    private int nbPlaylists;
    private boolean optionOffline;

    public AbonnementMusique(String nom, double prixBase, int nbProfils, int nbPlaylists, boolean optionOffline) {
        super(nom, prixBase, nbProfils);
        this.nbPlaylists = nbPlaylists;
        this.optionOffline = optionOffline;
    }

    public int getNbPlaylists() { return nbPlaylists; }
    public void setNbPlaylists(int nbPlaylists) { this.nbPlaylists = nbPlaylists; }

    public boolean isOptionOffline() { return optionOffline; }
    public void setOptionOffline(boolean optionOffline) { this.optionOffline = optionOffline; }

    @Override
    public double calculerCoutMensuel() {
        double cout = getPrixBase();
        if (optionOffline) cout += 15.0;
        return cout;
    }

    @Override
    public int calculerScoreSatisfaction() {
        int score = 50; // Valeur initiale
        if (nbPlaylists > 20) score += 20;
        if (optionOffline) score += 20;
        if (getNbProfils() > 1) score += 10;
        
        return Math.min(score, 100);
    }

    @Override
    public void afficherInfos() {
        System.out.println("--- Abonnement Musique ---");
        super.afficherInfos();
        System.out.println("Nombre de playlists: " + nbPlaylists);
        System.out.println("Option hors ligne: " + (optionOffline ? "Activée" : "Désactivée"));
    }

    @Override
    public boolean estEligibleReduction(double pourcentage) {
        return pourcentage <= 30.0;
    }

    @Override
    public double appliquerReduction(double pourcentage) {
        if (estEligibleReduction(pourcentage)) {
            return calculerCoutMensuel() * (1 - pourcentage / 100.0);
        }
        return calculerCoutMensuel();
    }
}
```

## 5. Classe `AbonnementJeux.java`

```java
package tp5;

public class AbonnementJeux extends Abonnement {
    private int nbJeuxInclus;
    private int heuresJeuParMois;

    public AbonnementJeux(String nom, double prixBase, int nbProfils, int nbJeuxInclus, int heuresJeuParMois) {
        super(nom, prixBase, nbProfils);
        this.nbJeuxInclus = nbJeuxInclus;
        this.heuresJeuParMois = heuresJeuParMois;
    }

    public int getNbJeuxInclus() { return nbJeuxInclus; }
    public void setNbJeuxInclus(int nbJeuxInclus) { this.nbJeuxInclus = nbJeuxInclus; }

    public int getHeuresJeuParMois() { return heuresJeuParMois; }
    public void setHeuresJeuParMois(int heuresJeuParMois) { this.heuresJeuParMois = heuresJeuParMois; }

    @Override
    public double calculerCoutMensuel() {
        double cout = getPrixBase();
        if (nbJeuxInclus > 50) cout += 25.0;
        if (heuresJeuParMois > 40) cout += 15.0;
        return cout;
    }

    @Override
    public int calculerScoreSatisfaction() {
        int score = 40; // Valeur initiale
        if (nbJeuxInclus >= 30) score += 20;
        if (heuresJeuParMois >= 20) score += 20;
        if (getNbProfils() >= 2) score += 10;
        
        return Math.min(score, 100);
    }

    @Override
    public void afficherInfos() {
        System.out.println("--- Abonnement Jeux Vidéo ---");
        super.afficherInfos();
        System.out.println("Nombre de jeux inclus: " + nbJeuxInclus);
        System.out.println("Heures de jeu par mois: " + heuresJeuParMois);
    }
}
```

## 6. Classe `TestAbonnements.java`

```java
package tp5;

public class TestAbonnements {
    public static void main(String[] args) {
        Abonnement[] abonnements = new Abonnement[3];
        abonnements[0] = new AbonnementVideo("Netflix", 80.0, 4, true, true);
        abonnements[1] = new AbonnementMusique("Spotify", 40.0, 2, 25, true);
        abonnements[2] = new AbonnementJeux("Xbox Game Pass", 120.0, 1, 60, 45);

        System.out.println("========== INFORMATIONS DES ABONNEMENTS ==========\n");
        
        for (Abonnement ab : abonnements) {
            ab.afficherInfos();
            System.out.println("-> Coût mensuel réel : " + ab.calculerCoutMensuel() + " DH");
            System.out.println("-> Score de satisfaction : " + ab.calculerScoreSatisfaction() + "/100\n");
        }

        System.out.println("========== TEST DES RÉDUCTIONS ==========\n");

        Reducible[] reducibles = new Reducible[2];
        reducibles[0] = (Reducible) abonnements[0]; 
        reducibles[1] = (Reducible) abonnements[1]; 

        double[] testsReduction = {20.0, 50.0};
        
        for (Reducible r : reducibles) {
            Abonnement ab = (Abonnement) r; 
            System.out.println("Test de réduction pour : " + ab.getNom());
            
            for (double pourcentage : testsReduction) {
                System.out.print(" - Réduction de " + pourcentage + "% : ");
                if (r.estEligibleReduction(pourcentage)) {
                    System.out.println("Acceptée ! Nouveau prix = " + r.appliquerReduction(pourcentage) + " DH");
                } else {
                    System.out.println("Refusée. Le prix reste à " + r.appliquerReduction(pourcentage) + " DH");
                }
            }
            System.out.println();
        }
    }
}
```

## 7. Résultats de l'exécution

```text
========== INFORMATIONS DES ABONNEMENTS ==========

--- Abonnement Vidéo ---
Nom de l'abonnement: Netflix
Prix de base: 80.0 DH
Nombre de profils: 4
Partage Familial: Oui
Option HD: Activée
Option 4K: Activée
-> Coût mensuel réel : 110.0 DH
-> Score de satisfaction : 100/100

--- Abonnement Musique ---
Nom de l'abonnement: Spotify
Prix de base: 40.0 DH
Nombre de profils: 2
Partage Familial: Non
Nombre de playlists: 25
Option hors ligne: Activée
-> Coût mensuel réel : 55.0 DH
-> Score de satisfaction : 100/100

--- Abonnement Jeux Vidéo ---
Nom de l'abonnement: Xbox Game Pass
Prix de base: 120.0 DH
Nombre de profils: 1
Partage Familial: Non
Nombre de jeux inclus: 60
Heures de jeu par mois: 45
-> Coût mensuel réel : 160.0 DH
-> Score de satisfaction : 80/100

========== TEST DES RÉDUCTIONS ==========

Test de réduction pour : Netflix
 - Réduction de 20.0% : Acceptée ! Nouveau prix = 88.0 DH
 - Réduction de 50.0% : Refusée. Le prix reste à 110.0 DH

Test de réduction pour : Spotify
 - Réduction de 20.0% : Acceptée ! Nouveau prix = 44.0 DH
 - Réduction de 50.0% : Refusée. Le prix reste à 55.0 DH
```
