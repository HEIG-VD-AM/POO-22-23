package engine.pieces;

import chess.ChessView;
import engine.ChessBoard;
import chess.PieceType;
import chess.PlayerColor;
import engine.moves.Move;
import engine.utils.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite qui permet de représenter une pièce.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public abstract class Piece implements ChessView.UserChoice {

    /**
     * Type de la pièce
     */
    private final PieceType type;

    /**
     * Couleur de la pièce
     */
    private final PlayerColor color;

    /**
     * Position de la pièce
     */
    private Vector position;

    /**
     * Liste des mouvements légaux d'une pièce.
     */
    protected List<Move> legalMoves = new ArrayList<>();

    /**
     * Indique le tour du premier mouvement de la pièce.
     */
    private int firstTurnMove = -1;

    /**
     * Constructeur de la classe Piece.
     *
     * @param pieceType   Le type de la pièce.
     * @param color La couleur de la pièce.
     * @param position    La position de la pièce.
     */
    public Piece(PieceType pieceType, PlayerColor color, Vector position) {
        this.type = pieceType;
        this.color = color;
        this.position = position;
    }

    /**
     * Permet de parcourir les mouvements légaux d'une pièce pour trouver un
     * mouvement réalisable selon la case choisie.
     *
     * @param position La position de la case choisie.
     * @return true si le mouvement est réalisable, false sinon.
     */
    public boolean canMove(Vector position, ChessBoard board) {

        // Parcours des mouvements légaux
        for (Move move : legalMoves) {
            if (move.canMove(this.getPosition(), position, board)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Récupère la couleur de la pièce.
     *
     * @return Couleur de la pièce.
     */
    public PlayerColor getColor() {
        return color;
    }

    /**
     * Récupère le type de la pièce.
     *
     * @return Type de la pièce.
     */
    public PieceType getType() {
        return type;
    }

    /**
     * Récupère la posiiton de la pièce.
     *
     * @return Position de la pièce.
     */
    public Vector getPosition() {
        return position;
    }

    /**
     * Défini la position de la pièce.
     *
     * @param position Nouvelle position
     */
    public void setPosition(Vector position) {
        this.position = position;
    }

    /**
     * Indique si la pièce peut être prise en passant
     *
     * @return true si la pièce peut être prise en passant, false sinon.
     */
    public boolean canEnPassant() {
        return false;
    }

    /**
     * Indique si la pièce a déjà bougé
     *
     * @return true si la pièce a déjà bougé, false sinon.
     */
    public int getFirstTurnMove() {
        return firstTurnMove;
    }

    /**
     * Défini si la pièce a déjà bougé
     *
     * @param turn Nouvelle valeur
     */
    public void setFirstTurnMove(int turn) {
        firstTurnMove = turn;
    }

    /**
     * Permet d'afficher la pièce dans la chess view.
     *
     * @return Valeur de la pièce.
     */
    public String toString() {
        return textValue();
    }

    /**
     * Tableau des mouvements réalisables par une pièce.
     *
     * @return Copie de la liste des mouvements réalisables.
     */
    public List<Move> getLegalMoves() {
        return new ArrayList<>(legalMoves);
    }
}
