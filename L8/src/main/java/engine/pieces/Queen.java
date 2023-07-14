package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.moves.EatingMove;
import engine.utils.Direction;
import engine.utils.Vector;

/**
 * Classe qui représente la pièce Reine.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class Queen extends Piece {

    /**
     * Constructeur de la classe Queen.
     *
     * @param color La couleur de la pièce.
     * @param position    La position de la pièce.
     */
    public Queen(PlayerColor color, Vector position) {
        super(PieceType.QUEEN, color, position);

        // Mouvements autorisés d'une pièce
        legalMoves.add(new EatingMove(this, Direction.NORTH));
        legalMoves.add(new EatingMove(this, Direction.SOUTH));
        legalMoves.add(new EatingMove(this, Direction.EAST));
        legalMoves.add(new EatingMove(this, Direction.WEST));
        legalMoves.add(new EatingMove(this, Direction.NORTH_EAST));
        legalMoves.add(new EatingMove(this, Direction.NORTH_WEST));
        legalMoves.add(new EatingMove(this, Direction.SOUTH_EAST));
        legalMoves.add(new EatingMove(this, Direction.SOUTH_WEST));
    }

    /**
     * Permet d'afficher la pièce dans la chess view.
     *
     * @return Valeur de la pièce.
     */
    public String textValue() {
        return "Queen";
    }

}
