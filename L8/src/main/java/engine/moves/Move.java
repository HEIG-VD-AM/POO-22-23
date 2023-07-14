package engine.moves;

import engine.ChessBoard;
import engine.pieces.Piece;
import engine.utils.Vector;

/**
 * Classe abstraite qui représente un mouvement en général.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public abstract class Move {

    /**
     * Pièce réalisant le mouvement.
     */
    private final Piece piece;

    /**
     * Constructeur de la classe Move.
     *
     * @param piece La pièce qui effectue le mouvement.
     */
    public Move(Piece piece) {

        this.piece = piece;
    }

    /**
     * Indique si le mouvement est possible entre les cases de départ et la case d'arrivée.
     *
     * @param from La case de départ.
     * @param to   La case d'arrivée.
     * @return true si le mouvement est possible, false sinon.
     */
    public abstract boolean canMove(Vector from, Vector to, ChessBoard board);

    /**
     * Pièce réalisant le mouvement.
     *
     * @return La pièce réalisant le mouvement.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Indique si le mouvement est un mouvement qui peut mettre en échec.
     *
     * @return True  --> Mouvement qui peut mettre en échec
     * False --> Mouvement qui ne peut pas mettre en échec
     */
    public boolean canCheck() {
        return true;
    }
}
