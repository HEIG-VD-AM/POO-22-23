package engine.moves;

import engine.ChessBoard;
import engine.pieces.Piece;
import engine.utils.Direction;
import engine.utils.Vector;

import java.util.List;

/**
 * Classe qui permet de représenter le mouvement du grand et du petit roque.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class CastlingMove extends DifferentFirstMove {

    /**
     * Constructeur de la classe CastlingMove.
     *
     * @param piece     La pièce qui effectue le mouvement.
     * @param direction La direction dans laquelle la pièce se déplace.
     * @param distance  La distance maximale parcourue par la pièce.
     */
    public CastlingMove(Piece piece, Direction direction, int distance) {
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

        /*
         * On vérifie que la distance souhaitée est bien inférieure
         * ou égalent à la distance maximale
         */
        int distance = from.chebyshevDistance(to);
        List<Vector> positions = from.trace(distance, getDirection());

        /*
         * On vérifie que les cases intermédiaires ne soient pas menacées
         */
        for (Vector position : positions) {
            if (board.isThreatened(position, getPiece().getColor())) {
                return false;
            }
        }

        // On récupère la tour qui va servir au roque
        Piece rook = board.getPiece(getDirection() == Direction.WEST ?
                new Vector(0, from.getY()) : new Vector(7, from.getY()));
        if (rook == null) {
            return false;
        }

        Vector rookPosition = new Vector(to.getX() -
                getDirection().getDirection().getX(), to.getY());

        // On vérifie que la tour puisse bouger jusqu'à la position voulue
        boolean canMove = rook.getFirstTurnMove() == -1 &&
                rook.canMove(rookPosition, board) &&
                super.canMove(from, to, board);

        // On enregistre la tour à bouger pour le roque
        if (canMove) {
            board.setDidCastle(true);
            board.setSecondPieceInvolvedInLastMove(rook);
        }

        return canMove;
    }
}
