package app;

/**
 * Anneau.java
 * @author Leandro Saraiva Maia
 * @author Hugo Ducommun
 * @author Joris Décoppet
 * @author Alexis Martins
 */
public abstract class Anneau {
    private String nom;
    protected Personne proprietaire;
    protected final Personne createur;
    protected final Lieu lieuDeCreation;

    public Anneau(String nom, Personne proprietaire) {
        this.nom = nom;
        this.createur = this.proprietaire = proprietaire;
        lieuDeCreation = createur.getLieu();

        System.out.printf("%s: création de l'anneau %s par %s !\n", lieuDeCreation, nom, createur);
        System.out.printf("%s possède l'anneau %s.\n", createur, nom);
    }

    /**
     * Détruit l'âne-eau gastrique
     */
    public void detruire() {
        System.out.printf("%s: %s tente de détruire l'anneau %s...\n", proprietaire.getLieu(), proprietaire, nom);
        if (proprietaire.getLieu() == lieuDeCreation) {
            System.out.printf("L'anneau %s est détruit.\n", nom);
            createur.mourir();
        } else {
            System.out.printf("L'anneau %s ne peut être détruit que là où il a été créé.\n", nom);
        }
    }

    public abstract void utiliser();

    public void definirProprietaire(Personne proprietaire) {
        this.proprietaire = proprietaire;
        System.out.printf("%s possède l'anneau %s.\n", proprietaire, nom);
    }
}