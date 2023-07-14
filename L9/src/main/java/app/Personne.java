package app;

/**
 * Personne.java
 * @author Leandro Saraiva Maia
 * @author Hugo Ducommun
 * @author Joris Décoppet
 * @author Alexis Martins
 */
import java.util.ArrayList;

public class Personne {
    protected String nom;
    private Lieu lieu;

    private static final ArrayList<Personne> personnes = new ArrayList<>();

    public Personne(String nom, Lieu lieu) {
        this.nom = nom;
        this.lieu = lieu;
        personnes.add(this);
    }

    public void afficherPersonnes() {
        for (Personne p : personnes) {
            System.out.println(p);
        }
    }

    /**
     * Une personne change de lieu
     * @param lieu Le nouveau lieu
     */
    public void deplacer(Lieu lieu) {
        System.out.println(nom + " se déplace: " + lieu);
        this.lieu = lieu;
    }

    public void mourir() {
        System.out.printf("%s meurt!\n", nom);
    }

    public Lieu getLieu() {
        return lieu;
    }

    @Override
    public String toString() {
        return nom;
    }
}
