package engine.moves;

import engine.ChessBoard;
import engine.pieces.Piece;
import engine.utils.Vector;

/**
 * Classe qui représente le mouvement d'un cavalier.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class KnightMove extends Move {

    /**
     * Constructeur de la classe KnightMove.
     *
     * @param piece La pièce qui effectue le mouvement.
     */
    public KnightMove(Piece piece) {
        super(piece);
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

        /*
         * On vérifie que la case d'arrivée est libre ou qu'elle contient
         * une pièce adverse.
         * On vérifie aussi que la distance soit correcte.
         */
        return (board.isFree(to) ||
                board.getPiece(to).getColor() !=
                        board.getPiece(from).getColor()) &&
                from.distanceSqr(to) == 5;
    }
}
