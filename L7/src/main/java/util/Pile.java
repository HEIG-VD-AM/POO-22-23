package util;

import java.util.*;

/**
 * Cette classe permet de modéliser la structure de données Pile.
 * Elle possède une classe interne modélisant ses éléments.
 * @author Decoppet Joris, Ducommun Hugo, Martins Alexis
 */
public class Pile {

    /**
     * Cette classe représente un élément de la pile.
     * @author Decoppet Joris, Ducommun Hugo, Martins Alexis
     */
    static class Element {
        // Donnée stockée dans l'élément
        final Object donnee;
        // Elément suivant dans la pile
        Element suivant;

        /**
         * Constructeur de la classe Element.
         * @param donnee Donnée à stocker dans l'élément
         */
        Element(Object donnee) {
            this.donnee = donnee;
            this.suivant = null;
        }
    }
    // Elément au sommet de la pile
    private Element sommet;
    // Taille de la pile
    private int taille;

    /**
     * Constructeur de la classe Pile.
     */
    public Pile() {

        this.taille = 0;
    }

    /**
     * Indique si la pile est vide
     * @return Vrai --> La pile est vide
     *         Faux --> La pile n'est pas vide
     */
    public boolean estVide() {

        return this.taille == 0;
    }

    /**
     * Retourne la taille de la pile
     * @return taille de la pile
     */
    public int obtenirTaille() {

        return this.taille;
    }

    /**
     * Rajoute un élément au sommet de la pile
     * @param donnee Elément à rajouter
     */
    public void empiler(Object donnee) {

        Element nouveauPremier = new Element (donnee);
        /*
         * Ajout du nouveau sommet de la pile et le précédent sommet est
         * identifié comme le suivant du sommet actuel
         */
        nouveauPremier.suivant = this.sommet;
        this.sommet = nouveauPremier;
        ++this.taille;
    }

    /**
     * Enlève l'élément qui est au sommet de la pile et retourne sa valeur.
     * @return Valeur de l'élément supprimé
     * @throws NoSuchElementException Génère une exception si la pile est vide.
     */
    public Object desempiler() {

        // On ne peut pas désempiler s'il n'y a rien sur la pile
        if (this.estVide())
            throw new NoSuchElementException();


        Object donnee = this.sommet.donnee;
        this.sommet = this.sommet.suivant;
        --this.taille;
        return donnee;
    }

    /**
     * Création d'un itérateur sur le sommet de la pile
     * @return Itérateur sur le sommet de la pile.
     */
    public Iterateur iterateur() {
        return new Iterateur(sommet);
    }

    /**
     * Retourne la représentation textuelle de la pile.
     * @return Chaîne de caractères représentant la pile.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterateur iterateur = iterateur();

        sb.append("[");

        while(iterateur.possedeSuivant()) {
            sb.append(" ");
            sb.append("<");
            sb.append(iterateur.suivant());
            sb.append(">");
        }

        return sb.append(" ]").toString();
    }

    /**
     * Tableau unidimensionnel qui représente l'état de la pile avec ses éléments.
     * @return Tableau unidimensionnel représentant l'état de la pile.
     */
    public Object[] etatPile() {

        Object[] etat = new Object[taille];
        Iterateur iterateur = iterateur();
        int i = 0;

        // Parcourir tous les éléments jusqu'à arriver en fin de pile
        while(iterateur.possedeSuivant()) {
            etat[i++] = iterateur.suivant();
        }

        return etat;
    }
}