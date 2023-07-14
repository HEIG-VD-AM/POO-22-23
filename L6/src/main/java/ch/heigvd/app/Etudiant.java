package ch.heigvd.app;

/**
 * Classe représentant un étudiant.
 * Un étudiant peut être membre d'un groupe et lui est attribué un matricule.
 *
 * @author Joris Décoppet
 * @author Hugo Ducommun
 * @author Alexis Martins
 * Date : 18.11.2022
 */
public class Etudiant extends Personne {
    private final int matricule;
    private Groupe groupe;

    /**
     * Constructeur de l'étudiant sans groupe
     * @param prenom    Prénom de l'étudiant
     * @param nom       Nom de l'étudiant
     * @param matricule Format "XXXX" où XXXX est un nombre
     */
    public Etudiant(String prenom, String nom, int matricule){
        super(prenom, nom);
        this.matricule = matricule;
    }

    /**
     * Retourne le nom de l'étudiant et son matricule
     * @return Ex : "Etud. Dupont Patrick (#1234) - IL6-1"
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString() + " (#" + matricule + ")");
        if (this.groupe != null)
            sb.append(" - ").append(this.groupe.nom());
        return sb.toString();
    }

    /**
     * Setter du groupe visible au niveau package
     */
    void definirGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
}
