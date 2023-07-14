package ch.heigvd.app;

import ch.heigvd.app.operateur.*;

/**
 * Programme principal qui permet de valider les principes généraux de
 * notre classe Matrice.
 *
 * Reproduction de l'affichage sur la consigne de laboratoire.
 *
 * @author Joris Decoppet
 * @author Hugo Ducommun
 * @author Alexis Martins
 *
 * Date : 11.11.2022
 */
public class App {
    /**
     * Affichage d'une matrice avec un titre au-dessus de celle-ci
     *
     * @param m     Matrice à afficher
     * @param title Titre donné à la matrice
     */
    public static void afficherMatrice(Matrice m, String title) {

        System.out.println(title + ":");
        System.out.println(m.toString());
    }

    public static void main(String[] args) {

        final int module = 5;

        final int x1 = 3; // Nb de lignes de la matrice 1
        final int y1 = 4; // Nb de colonnes de la matrice 1
        final int x2 = 3; // Nb de lignes de la matrice 2
        final int y2 = 5; // Nb de colonnes de la matrice 2

        // Génère des matrices à nombres aléatoires
        final Matrice m1 = new Matrice(x1, y1, module);
        final Matrice m2 = new Matrice(x2, y2, module);

        System.out.println("The modulus is " + module);

        afficherMatrice(m1, "one");
        afficherMatrice(m2, "two");
        afficherMatrice(m1.calculer(m2, new Additionner()), "one + two");
        afficherMatrice(m1.calculer(m2, new Soustraire()), "one - two");
        afficherMatrice(m1.calculer(m2, new Multiplier()), "one x two");
    }
}
