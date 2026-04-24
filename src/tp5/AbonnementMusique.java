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
