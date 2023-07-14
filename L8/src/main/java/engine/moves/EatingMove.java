package engine.moves;

import engine.ChessBoard;
import engine.pieces.Piece;
import engine.utils.Direction;
import engine.utils.Vector;

/**
 * Représente les mouvements qui permettent de manger une pièce adverse.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class EatingMove extends MoveStraight {

    /**
     * Constructeur de la classe EatingMove.
     *
     * @param piece     La pièce qui effectue le mouvement.
     * @param direction La direction dans laquelle la pièce se déplace.
     * @param distance  La distance maximale parcourue par la pièce.
     */
    public EatingMove(Piece piece, Direction direction, int distance) {
        super(piece, direction, distance);
    }

    /**
     * Constructeur de la classe EatingMove.
     *
     * @param piece     La pièce qui effectue le mouvement.
     * @param direction La direction dans laquelle la pièce se déplace.
     */
    public EatingMove(Piece piece, Direction direction) {
        super(piece, direction);
    }

    /**
     * Indique si le mouvement est possible entre les cases de départ et la case d'arrivée.
     *
     * @param from La case de départ.
     * @param to   La case d'arrivée.
     * @return true si le mouvement est possible, false sinon.
     */
    public boolean canMove(Vector from, Vector to, ChessBoard board) {

        // Dans le cas d'une case occupée, il faut vérifier que la pièce est adverse.
        return  (board.isFree(to) ||
                board.getPiece(to).getColor() !=
                board.getPiece(from).getColor()) &&
                super.canMove(from, to, board);
    }
}