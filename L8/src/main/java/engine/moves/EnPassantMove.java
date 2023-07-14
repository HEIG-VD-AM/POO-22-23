package engine.moves;

import chess.PlayerColor;
import engine.ChessBoard;
import engine.pieces.Piece;
import engine.utils.Direction;
import engine.utils.Vector;

/**
 * Classe qui représente le mouvement en passant d'un pion.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class EnPassantMove extends Move {

    /**
     * Direction du mouvement en passant
     */
    private final Direction direction;

    /**
     * Constructeur de la classe EnPassantMove.
     *
     * @param piece     La pièce qui effectue le mouvement.
     * @param direction La direction dans laquelle la pièce se déplace.
     */
    public EnPassantMove(Piece piece, Direction direction) {
        super(piece);
        this.direction = direction;
    }

    /**
     * Indique si le mouvement est possible entre les cases de départ et la case d'arrivée.
     *
     * @param from La case de départ.
     * @param to   La case d'arrivée.
     * @return true si le mouvement est possible, false sinon.
     */
    public boolean canMove(Vector from, Vector to, ChessBoard board) {

        Piece lastMoved = board.getLastMovedPiece();

        /*
         * On vérifie que la pièce soit bien un pion et qu'il ait fait son
         * mouvement au dernier tour.
         */
        boolean canMove = (getPiece().getColor() == PlayerColor.WHITE ?
                from.getY() == 4 : from.getY() == 3) &&
                lastMoved != null &&
                lastMoved.canEnPassant() &&
                lastMoved.getFirstTurnMove() == board.getTurn() - 1 &&
                lastMoved.getColor() != getPiece().getColor() &&
                board.getLastMovedPiece().getPosition().equals(
                        to.sub(new Vector(0, direction.getDirection().getY())));

        // On enregistre la pièce qui va être mangée.
        if (canMove) {
            board.setSecondPieceInvolvedInLastMove(board.getPiece(
                    to.sub(new Vector(0, direction.getDirection().getY()))));
            board.setDidEnPassant(true);
        }

        return canMove;

    }
}
