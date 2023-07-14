package util;

/**
 * Classe itérateur qui pointe sur un élément.
 * @author Decoppet Joris, Ducommun Hugo, Martins Alexis
 */
class Iterateur {
    // Elément représenté par l'itérateur
    private Pile.Element element;

    /**
     * Constructeur de la classe Iterateur.
     * @param e Elément qui est représenté par l'itérateur.
     */
    public Iterateur(Pile.Element e) {
        this.element = e;
    }

    /**
     * Fait passer l'itérateur au prochain élément et
     * renvoie la valeur de l'élément actuel.
     * @return Valeur de l'élément courant de l'itérateur.
     * @throws NullPointerException Génère une exception s'il ne possède
     * pas d'élément à sa suite.
     */
    public Object suivant() {
        if(!this.possedeSuivant()) {
            throw new NullPointerException("Aucun élément suivant");
        }

        Pile.Element actuel = this.element;
        this.element = this.element.suivant;
        return actuel.donnee;
    }

    /**
     * Indique si l'itérateur possède un élément suivant.
     * @return Vrai --> l'itérateur possède un élément suivant.
     *         Faux --> l'itérateur ne possède pas d'élément suivant.
     */
    public boolean possedeSuivant() {
        return this.element != null;
    }
}