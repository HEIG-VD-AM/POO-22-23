package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.moves.KnightMove;
import engine.utils.Vector;

/**
 * Classe représentant un cavalier
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class Knight extends Piece {

    /**
     * Constructeur de la classe Knight.
     *
     * @param color La couleur du joueur.
     * @param position    La position de la pièce.
     */
    public Knight(PlayerColor color, Vector position) {

        super(PieceType.KNIGHT, color, position);


        // Mouvements autorisés d'une pièce
        legalMoves.add(new KnightMove(this));
    }

    /**
     * Permet d'afficher la pièce dans la chess view.
     *
     * @return Valeur du cavalier.
     */
    public String textValue() {
        return "Knight";
    }
}
