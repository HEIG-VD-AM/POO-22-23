package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.moves.EatingMove;
import engine.utils.Direction;
import engine.utils.Vector;

/**
 * Classe qui représente la pièce Tour.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class Rook extends Piece {

    /**
     * Constructeur de la classe Rook.
     *
     * @param color La couleur de la pièce.
     * @param position    La position de la pièce.
     */
    public Rook(PlayerColor color, Vector position) {
        super(PieceType.ROOK, color, position);

        // Mouvements autorisés d'une pièce
        legalMoves.add(new EatingMove(this, Direction.SOUTH));
        legalMoves.add(new EatingMove(this, Direction.NORTH));
        legalMoves.add(new EatingMove(this, Direction.EAST));
        legalMoves.add(new EatingMove(this, Direction.WEST));
    }

    /**
     * Permet d'afficher la pièce dans la chess view.
     *
     * @return Valeur de la pièce.
     */
    public String textValue() {
        return "Rook";
    }
}
