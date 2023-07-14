package app;

/**
 * Lieu.java
 * @author Leandro Saraiva Maia
 * @author Hugo Ducommun
 * @author Joris Décoppet
 * @author Alexis Martins
 */
public enum Lieu {

    Comte("La Comté"),
    Destin("Le Mont du Destin");

    private final String lieu;

    Lieu(String lieu) {
        this.lieu = lieu;
    }

    public String toString() {
        return lieu;
    }
}
