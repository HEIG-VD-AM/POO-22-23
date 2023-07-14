package ch.heigvd.app;

/**
 * Classe abstraite modélisant une personne.
 *
 * @author Joris Décoppet
 * @author Hugo Ducommun
 * @author Alexis Martins
 * Date : 18.11.2022
 */
public abstract class Personne {
    private final String prenom;
    private final String nom;

    /**
     * Constructeur d'une personne
     * @param prenom    Prénom d'une personne
     * @param nom       Nom d'une personne
     */
    public Personne(String prenom, String nom){
        this.prenom = prenom;
        this.nom = nom;
    }

    /**
     * Renvoie le nom d'une personne selon sa classe la plus spécialisée
     * @return Ex : "Etud. Dupont Patrick"
     */
    public String toString() {
        return this.getClass().getSimpleName().substring(0, 4) + ". " + prenom + " " + nom;
    }
}
