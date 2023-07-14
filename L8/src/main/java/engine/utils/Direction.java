package engine.utils;

/**
 * Enumération des directions possibles sur un plateau.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public enum Direction {

    /**
     * Enumération des directions possibles
     */
    NORTH(0, 1), SOUTH(0, -1), EAST(1, 0), WEST(-1, 0), NORTH_EAST(1, 1), NORTH_WEST(-1, 1), SOUTH_EAST(1, -1), SOUTH_WEST(-1, -1);

    /**
     * Vecteur représentant la direction
     */
    private final Vector direction;

    /**
     * Constructeur de Direction.
     */
    Direction(int i, int j) {
        this.direction = new Vector(i, j);
    }

    /**
     * Retourne le vecteur représentant la direction
     */
    public Vector getDirection() {
        return direction;
    }
}