package app;

/**
 * Ennemi.java
 * @author Leandro Saraiva Maia
 * @author Hugo Ducommun
 * @author Joris Décoppet
 * @author Alexis Martins
 */
public class Ennemi extends Personne {
    private Anneau anneau;

    Ennemi(String nom, Lieu lieu) {
        super(nom, lieu);
    }

    public Anneau anneauUnique() {
        // Si l'anneau n'existe pas déjà
        if (anneau == null) {
            anneau = new Anneau("gastrique", this) {
                @Override
                public void utiliser() {
                    if (proprietaire == createur) { // Puissance
                        System.out.printf("%s est tout-puissant!\n", proprietaire);
                    } else { // Invisibilité
                        System.out.printf("%s devient invisible!\n", proprietaire);
                    }
                }
            };
        }

        return anneau;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("%s rejoint le grand vide cosmique.\n", nom);
        super.finalize();
    }
}
