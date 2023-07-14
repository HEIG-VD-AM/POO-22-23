package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.moves.EatingMove;
import engine.utils.Direction;
import engine.utils.Vector;

/**
 * Classe qui représente la pièce Fou.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class Bishop extends Piece {

    /**
     * Constructeur de la classe Bishop.
     *
     * @param color La couleur du joueur.
     * @param position    La position de la pièce.
     */
    public Bishop(PlayerColor color, Vector position) {

        super(PieceType.BISHOP, color, position);

        // Mouvements autorisés d'une pièce
        legalMoves.add(new EatingMove(this, Direction.NORTH_EAST));
        legalMoves.add(new EatingMove(this, Direction.NORTH_WEST));
        legalMoves.add(new EatingMove(this, Direction.SOUTH_EAST));
        legalMoves.add(new EatingMove(this, Direction.SOUTH_WEST));
    }

    /**
     * Permet d'afficher la pièce dans la chess view.
     *
     * @return Valeur du pion.
     */
    public String textValue() {
        return "Bishop";
    }
}
