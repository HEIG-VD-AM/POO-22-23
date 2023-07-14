package engine.utils;

import engine.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un vecteur.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class Vector {

    // Composante X
    private final int x;

    // Composante Y
    private final int y;

    /**
     * Constructeur de la classe Vector.
     *
     * @param x Composante X
     * @param y Composante Y
     */
    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructeur de la classe Vector.
     *
     * @param piece Pièce dont on souhaite copier la position.
     */
    public Vector(Piece piece) {
        this(piece.getPosition().getX(), piece.getPosition().getY());
    }

    /**
     * Permet d'obtenir la composante X.
     *
     * @return Composante X
     */
    public int getX() {
        return x;
    }

    /**
     * Permet d'obtenir la composante Y.
     *
     * @return Composante Y
     */
    public int getY() {
        return y;
    }

    /**
     * Récupérer la distance au carré entre deux positions
     *
     * @param other l'autre position
     * @return la distance euclidienne au carré entre les deux positions
     */
    public int distanceSqr(Vector other) {
        return ((x - other.x) * (x - other.x)) + ((y - other.y) * (y - other.y));
    }

    /**
     * Récupérer la distance entre deux positions sur une grille
     *
     * @param other l'autre position
     * @return la distance de Chebyshev entre les deux positions
     * @see <a href="https://www.chessprogramming.org/Distance">Documentation sur la distance de Chebyshev</a>
     */
    public int chebyshevDistance(Vector other) {
        return Math.max(Math.abs(x - other.x), Math.abs(y - other.y));
    }

    /**
     * Créer une nouvelle position dans la quelle on ajoute les coordonnées
     * de la position courante et de la position passée en paramètre
     *
     * @param other la position à ajouter
     * @return la nouvelle position
     */
    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }

    /**
     * Créer une nouvelle position dans laquelle on soustrait aux coordonnées de la position courante
     * les coordonnées de la position passée en paramètre
     *
     * @param other la position à soustraire
     * @return la nouvelle position
     */
    public Vector sub(Vector other) {
        return new Vector(x - other.x, y - other.y);
    }

    /**
     * Créer une nouvelle position dans laquelle on multiplie par un scalaire
     * les coordonnées de la position courante
     *
     * @param scalar le scalaire multiplicateur
     * @return la nouvelle position
     */
    public Vector mul(int scalar) {
        return new Vector(x * scalar, y * scalar);
    }

    /**
     * Trace un segment depuis la position et d'une longueur maxDistance dans la direction de orientation
     * puis récupère la liste des positions par lesquelles le segment passe.
     *
     * @param distance    la longueur maximale du segment
     * @param orientation la direction du segment
     * @return la liste des positions par lesquelles le segment passe
     */
    public List<Vector> trace(int distance, Direction orientation) {
        List<Vector> pos = new ArrayList<>();
        Vector direction = orientation.getDirection();
        for (int i = 1; i <= distance; ++i) {
            pos.add(this.add(direction.mul(i)));
        }
        return pos;
    }

    /**
     * Permet de savoir si deux vecteurs sont de même direction.
     *
     * @param unitVectorOrientation Second vecteur
     * @return true si les deux vecteurs sont de même direction, false sinon
     */
    public boolean isSameDirection(Vector unitVectorOrientation) {

        return (this.getX() * unitVectorOrientation.getY() != this.getY() *
                unitVectorOrientation.getX()
                || (this.getX() < 0 != unitVectorOrientation.getX() < 0
                || this.getY() < 0 != unitVectorOrientation.getY() < 0));
    }

    @Override
    public String toString() {
        return "Vector(" + x + ", " + y + ")";
    }

    public boolean equals(Vector other) {
        return this.x == other.x && this.y == other.y;
    }
}
