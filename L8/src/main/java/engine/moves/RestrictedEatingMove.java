package engine.moves;

import engine.ChessBoard;
import engine.pieces.Piece;
import engine.utils.Direction;
import engine.utils.Vector;

/**
 * Classe qui représente le mouvement d'un pion qui mange en diagonale.
 * Il ne peut pas aller sur la case si celle-ci est vide. Il doit absolument y
 * avoir une pièce pour la manger et prendre sa position.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class RestrictedEatingMove extends EatingMove {

    /**
     * Constructeur de la classe RestrictedEatingMove.
     *
     * @param piece       La pièce qui effectue le mouvement.
     * @param direction   La direction dans laquelle la pièce se déplace.
     * @param maxDistance La distance maximale parcourue par la pièce.
     */
    public RestrictedEatingMove(Piece piece, Direction direction, int maxDistance) {
        super(piece, direction, maxDistance);
    }

    /**
     * Indique si le mouvement est possible entre les cases de départ et la case d'arrivée.
     *
     * @param from La case de départ.
     * @param to   La case d'arrivée.
     * @return true si le mouvement est possible, false sinon.
     */
    @Override
    public boolean canMove(Vector from, Vector to, ChessBoard board) {
        return !board.isFree(to) && super.canMove(from, to, board);
    }
}

