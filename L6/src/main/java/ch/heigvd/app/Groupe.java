package ch.heigvd.app;

import java.util.ArrayList;

/**
 * Classe représentant un groupe d'étudiants qui suit une liste de leçons
 * Chaque groupe fait partie d'une orientation (IL, IE, ...) et d'un trimestre
 *
 * @author Joris Décoppet
 * @author Hugo Ducommun
 * @author Alexis Martins
 * Date : 18.11.2022
 */
public class Groupe {
    private final int numero;
    private final String orientation;
    private final int trimestre;
    private final Etudiant[] listeEtudiants;
    private final ArrayList<Lecon> listeLecon;

    /**
     * Constructeur du groupe
     * @param numero            Numéro du semestre du bachelor (1-6)
     * @param orientation       Orientation du groupe (IL, IE, SI, ID, RS)
     * @param trimestre         Numéro du trimestre (1-4)
     * @param listeEtudiants    Etudiants du groupe
     * @throws RuntimeException Si le nombre d'étudiants est inférieur à 1
     */
    public Groupe(int numero, String orientation, int trimestre,
                  Etudiant[] listeEtudiants) throws IllegalArgumentException {
        this.numero = numero;
        this.orientation = orientation;
        this.trimestre = trimestre;
        this.listeEtudiants = listeEtudiants;
        this.listeLecon = new ArrayList<Lecon>();
        if(listeEtudiants.length < 1){
            throw new IllegalArgumentException("Le groupe doit avoir au moins un étudiant");
        }
        for(Etudiant etudiant : this.listeEtudiants) {
            etudiant.definirGroupe(this);
        }
    }

    /**
     * Affiche l'horaire de la semaine d'un groupe sous forme de tableau d'horaire
     * @return L'horaire au format grille
     */
    public String horaire() {
        return "-- Horaire du groupe " + nom() + " (" + nombreEtudiants() + " etudiant"
                + (listeEtudiants.length > 1 ? "s" : "") + ")\n" + Lecon.horaire(listeLecon);
    }

    /**
     * Affiche le groupe et ses attributs
     * @return Ex : "IL6-3"
     */
    public String nom() {
        return orientation + numero + "-" + trimestre;
    }

    /**
     * Renvoie le nombre d'étudiants du groupe.
     * @return Le nombre d'étudiants dans le groupe
     */
    public int nombreEtudiants() {
        return listeEtudiants.length;
    }

    /**
     * Assigne de nouvelles leçons au groupe.
     * @param args Liste de leçons à affecter au groupe
     */
    public void definirLecons(Lecon ... args) {
        for(Lecon lecon : args) {
            listeLecon.add(lecon);
        }
    }
}
