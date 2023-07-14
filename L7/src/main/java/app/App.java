package app;

import hanoi.*;
import hanoi.gui.*;

/**
 * Classe principale du programme
 * @author Decoppet Joris, Ducommun Hugo, Martins Alexis
 */
public class App {

    /**
     * Méthode main de la classe principale
     * @param args Arguments du programme pour passer les nombres de
     * disques
     */
    public static void main(String[] args) {
        /*
         * Si lors de l'appel de notre classe, un argument est passé, alors
         * c'est que l'on souhaite afficher le jeu au format console.
         */
        if (args.length > 0) {
            int nbDisques = lireArgument(args);
            Hanoi hanoi = new Hanoi(nbDisques);
            hanoi.solve();
        } else {
            new JHanoi();
        }
    }

    /**
     * Verifier qu'il y ait bien un seul argument et qu'il soit un entier
     * @param args les arguments de la ligne de commande
     * @throws IllegalArgumentException Si on n'a pas un seul argument ou
     * si l'argument n'est pas un entier
     */
    private static int lireArgument(String[] args) {
        if (args.length > 1) {
            throw new IllegalArgumentException("Il faut au plus 1 argument. " +
                      "0 arguments pour l'affichage graphique, " +
                      "1 argument pour l'affichage console");
        }
        try {
            return Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Le nombre de disques " +
                                               "doit être un entier");
        }
    }
}