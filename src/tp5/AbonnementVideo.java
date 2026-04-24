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
