package engine.moves;

import engine.ChessBoard;
import engine.pieces.Piece;
import engine.utils.Direction;
import engine.utils.Vector;

/**
 * Classe qui représente les mouvements qui sont réalisables uniquement
 * lorsque la pièce n'a pas bougée.
 * Comme le mouvement de deux cases pour un pion ou le roque.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class DifferentFirstMove extends NotEatingMove {

    /**
     * Constructeur de la classe DifferentFirstMove.
     *
     * @param piece     La pièce qui effectue le mouvement.
     * @param direction La direction dans laquelle la pièce se déplace.
     * @param distance  La distance maximale parcourue par la pièce.
     */
    public DifferentFirstMove(Piece piece, Direction direction, int distance) {
        super(piece, direction, distance);
    }

    /**
     * Indique si le mouvement est possible entre les cases de départ et la case d'arrivée.
     *
     * @param from La case de départ.
     * @param to   La case d'arrivée.
     * @return true si le mouvement est possible, false sinon.
     */
    public boolean canMove(Vector from, Vector to, ChessBoard board) {

        // On peut réaliser le mouvement uniquement si la pièce n'a pas bougée.
        return getPiece().getFirstTurnMove() == -1 && super.canMove(from, to, board);
    }
}
