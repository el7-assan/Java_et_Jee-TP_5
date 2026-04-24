package tp5;

public class TestAbonnements {
    public static void main(String[] args) {
        // 1. Créer un tableau d’objets de type Abonnement
        Abonnement[] abonnements = new Abonnement[3];
        abonnements[0] = new AbonnementVideo("Netflix", 80.0, 4, true, true);
        abonnements[1] = new AbonnementMusique("Spotify", 40.0, 2, 25, true);
        abonnements[2] = new AbonnementJeux("Xbox Game Pass", 120.0, 1, 60, 45);

        System.out.println("========== INFORMATIONS DES ABONNEMENTS ==========\n");
        
        // 2. Pour chaque abonnement, afficher les infos, coût et score
        for (Abonnement ab : abonnements) {
            ab.afficherInfos();
            System.out.println("-> Coût mensuel réel : " + ab.calculerCoutMensuel() + " DH");
            System.out.println("-> Score de satisfaction : " + ab.calculerScoreSatisfaction() + "/100\n");
        }

        System.out.println("========== TEST DES RÉDUCTIONS ==========\n");

        // 3. Créer un tableau contenant uniquement les objets de type Reducible
        // (AbonnementVideo et AbonnementMusique implémentent Reducible)
        Reducible[] reducibles = new Reducible[2];
        reducibles[0] = (Reducible) abonnements[0]; // AbonnementVideo
        reducibles[1] = (Reducible) abonnements[1]; // AbonnementMusique

        // 4. Tester l’application d’une réduction de 20 % puis de 50 %
        double[] testsReduction = {20.0, 50.0};
        
        for (Reducible r : reducibles) {
            // Casting juste pour récupérer le nom pour l'affichage
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
