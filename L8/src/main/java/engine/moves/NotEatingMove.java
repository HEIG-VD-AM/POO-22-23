package engine.moves;

import engine.ChessBoard;
import engine.pieces.Piece;
import engine.utils.Direction;
import engine.utils.Vector;

/**
 * Classe qui représente les mouvements qui ne peuvent pas manger de pièce.
 * Typiquement dans le cas du pion lorsqu'il avance droit ou dans le cas d'un roque.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class NotEatingMove extends MoveStraight {

    /**
     * Constructeur de la classe NotEatingMove.
     *
     * @param piece     La pièce qui effectue le mouvement.
     * @param direction La direction dans laquelle la pièce se déplace.
     * @param distance  La distance maximale parcourue par la pièce.
     */
    public NotEatingMove(Piece piece, Direction direction, int distance) {
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
        return board.isFree(to) && super.canMove(from, to, board);
    }

    /**
     * Indique que le mouvement ne peut pas mettre en échec en se réalisant.
     *
     * @return True  --> Le mouvement ne peut pas mettre en échec
     * False --> Le mouvement peut mettre en échec
     */
    public boolean canCheck() {
        return false;
    }
}
