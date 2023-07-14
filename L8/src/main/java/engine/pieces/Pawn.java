package engine.pieces;

import chess.PieceType;
import engine.ChessBoard;
import chess.PlayerColor;
import engine.moves.*;
import engine.utils.Direction;
import engine.utils.Vector;

/**
 * Classe représentant un pion
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class Pawn extends Piece {

    /**
     * Constructeur de la classe Pawn.
     *
     * @param color La couleur du joueur.
     * @param position    La position de la pièce.
     */
    public Pawn(PlayerColor color, Vector position) {

        super(PieceType.PAWN, color, position);


        // Mouvements autorisés d'une pièce
        // Mouvements droits où le pion avance uniquement
        Direction straightMove = color == PlayerColor.WHITE ?
                Direction.NORTH : Direction.SOUTH;
        legalMoves.add(new DifferentFirstMove(this, straightMove, 2));
        legalMoves.add(new NotEatingMove(this, straightMove, 1));

        // Mouvement en passant vers un premier sens
        Direction diagonal1 = color == PlayerColor.WHITE ?
                Direction.NORTH_EAST : Direction.SOUTH_WEST;
        legalMoves.add(new EnPassantMove(this, diagonal1));
        legalMoves.add(new RestrictedEatingMove(this, diagonal1, 1));


        // Mouvement en passant vers un deuxième sens
        Direction diagonal2 = color == PlayerColor.WHITE ?
                Direction.NORTH_WEST : Direction.SOUTH_EAST;
        legalMoves.add(new EnPassantMove(this, diagonal2));
        legalMoves.add(new RestrictedEatingMove(this, diagonal2, 1));

    }

    /**
     * Indique que la pièce peut être mangée en passant.
     *
     * @return true si la pièce peut être mangée en passant.
     */
    public boolean canEnPassant() {
        return true;
    }

    /**
     * Vérifie si une pièce peut être promue.
     *
     * @return true si la pièce peut être promue, false sinon.
     */
    public boolean checkPromotion() {

        /*
         * Si le pion est sur la dernière ligne du plateau, alors il est
         * peut être promu
         */
        return getPosition().getY() == 0 ||
                getPosition().getY() == ChessBoard.BOARD_SIZE - 1;
    }

    /**
     * Permet d'afficher la pièce dans la chess view.
     *
     * @return Valeur de la pièce.
     */
    public String textValue() {
        return "Pawn";
    }
}
