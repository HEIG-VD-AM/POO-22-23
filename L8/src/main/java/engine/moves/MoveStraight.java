package engine.moves;

import engine.ChessBoard;
import engine.pieces.Piece;
import engine.utils.Direction;
import engine.utils.Vector;

import java.util.List;

/**
 * Classe qui représente un mouvement en ligne droite sur une distance donnée.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class MoveStraight extends Move {

    /**
     * Direction du mouvement rectiligne.
     */
    private final Direction direction;

    /**
     * Distance maximale du mouvement.
     */
    private final int moveMaxDistance;

    /**
     * Constructeur de la classe MoveStraight.
     *
     * @param piece     La pièce qui effectue le mouvement.
     * @param direction La direction dans laquelle la pièce se déplace.
     * @param distance  La distance maximale parcourue par la pièce.
     */
    public MoveStraight(Piece piece, Direction direction, int distance) {
        super(piece);
        this.direction = direction;
        this.moveMaxDistance = distance;
    }

    /**
     * Constructeur de la classe MoveStraight.
     *
     * @param piece     La pièce qui effectue le mouvement.
     * @param direction La direction dans laquelle la pièce se déplace.
     */
    public MoveStraight(Piece piece, Direction direction) {
        this(piece, direction, ChessBoard.BOARD_SIZE);
    }

    /**
     * Indique si le mouvement est possible entre les cases de départ et la case d'arrivée.
     *
     * @param from La case de départ.
     * @param to   La case d'arrivée.
     * @return True  --> Mouvement possible
     * False --> Mouvement impossible
     */
    public boolean canMove(Vector from, Vector to, ChessBoard board) {
        // Vérification que la distance soit dans les bonnes limites
        int distance;
        if ((distance = from.chebyshevDistance(to)) > moveMaxDistance || distance == 0) {
            return false;
        }

        // Vérification que les vecteurs aient le même sens et la même direction
        Vector unitVectorMove = to.sub(from);
        Vector unitVectorOrientation = direction.getDirection();
        if (unitVectorMove.isSameDirection(unitVectorOrientation)) {
            return false;
        }

        // Vérification que les cases intermédiaires soient vides
        List<Vector> positions = from.trace(distance - 1, direction);
        for (Vector position : positions) {
            if (!board.isFree(position)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Direction du mouvement.
     *
     * @return La direction du mouvement.
     */
    Direction getDirection() {
        return direction;
    }
}
