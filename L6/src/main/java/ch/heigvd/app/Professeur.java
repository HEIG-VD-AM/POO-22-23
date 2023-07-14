package ch.heigvd.app;

import java.util.ArrayList;

/**
 * Classe modélisant un professeur.
 *
 * @author Joris Décoppet
 * @author Hugo Ducommun
 * @author Alexis Martins
 * Date : 18.11.2022
 */
public class Professeur extends Personne {
    private final String abreviation;
    private final ArrayList<Lecon> listeLecon;

    /**
     * Constructeur de professeur
     * @param prenom        Prénom de l'enseignant
     * @param nom           Nom de l'enseignant
     * @param abreviation   Initiales de l'enseignant
     */
    public Professeur(String prenom, String nom, String abreviation){
        super(prenom, nom);
        this.listeLecon = new ArrayList<Lecon>();
        this.abreviation = abreviation;
    }

    /**
     * Ajoute une leçon donnée par ce professeur visible au niveau package
     */
    void definirLecon(Lecon lecon){
        this.listeLecon.add(lecon);
    }

    /**
     * Getter de l'abréviation de l'enseignant
     * @return abréviation de l'enseignant
     */
    public String abreviation() {
        return abreviation;
    }

    /**
     * Affiche les informations de l'enseignant
     * @return Nom de l'enseignant et abréviation
     */
    public String toString() {
        return super.toString() + " (" + abreviation() + ")";
    }

    /**
     * Retourne une chaîne de caractère contenant l'horaire de
     * l'enseignant au format de grille
     * @return Horaire de l'enseignant
     */
    public String horaire() {
        return "-- Horaire " + this + "\n" + Lecon.horaire(listeLecon);
    }
}
