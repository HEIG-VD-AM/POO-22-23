package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.moves.CastlingMove;
import engine.moves.EatingMove;
import engine.utils.Direction;
import engine.utils.Vector;


/**
 * Classe qui représente la pièce Roi.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class King extends Piece {

    /**
     * Constructeur de la classe King.
     *
     * @param color La couleur du joueur.
     * @param position    La position de la pièce.
     */
    public King(PlayerColor color, Vector position) {

        super(PieceType.KING, color, position);

        // Mouvements autorisés d'une pièce
        legalMoves.add(new EatingMove(this, Direction.NORTH, 1));
        legalMoves.add(new EatingMove(this, Direction.SOUTH, 1));
        legalMoves.add(new EatingMove(this, Direction.EAST, 1));
        legalMoves.add(new EatingMove(this, Direction.WEST, 1));
        legalMoves.add(new EatingMove(this, Direction.NORTH_EAST, 1));
        legalMoves.add(new EatingMove(this, Direction.NORTH_WEST, 1));
        legalMoves.add(new EatingMove(this, Direction.SOUTH_EAST, 1));
        legalMoves.add(new EatingMove(this, Direction.SOUTH_WEST, 1));
        legalMoves.add(new CastlingMove(this, Direction.EAST, 2));
        legalMoves.add(new CastlingMove(this, Direction.WEST, 2));
    }

    /**
     * Permet d'afficher la pièce dans la chess view.
     *
     * @return Valeur du pion.
     */
    public String textValue() {
        return "King";
    }
}
