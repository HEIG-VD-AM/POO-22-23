package engine;

import chess.PlayerColor;
import engine.moves.Move;
import engine.pieces.*;
import engine.utils.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente l'état du jeu sur le plateau. Elle permet de savoir
 * dans quelle disposition se trouvent les pièces.
 * Elle possède aussi la logique des actions par rapport aux cases.
 * Pour vérifier que celles-ci ne soient pas attaquées par une pièce adverse.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class ChessBoard {

    /**
     * Taille d'un côté du plateau de jeu
     */
    public final static int BOARD_SIZE = 8;

    /**
     * Tour courant
     */
    private int turn = 0;

    /**
     * Tableau de pièces représentant le plateau de jeu
     */
    private final Piece[][] state = new Piece[BOARD_SIZE][BOARD_SIZE];

    /**
     * Pièce impliquée dans le dernier mouvement effectué
     */
    private Piece secondPieceInvolvedInLastMove = null;

    /**
     * Roi blanc
     */
    private Piece whiteKing = null;

    /**
     * Roi noir
     */
    private Piece blackKing = null;

    /**
     * Liste des pions noirs
     */
    private final List<Pawn> blackPawns = new ArrayList<>();

    /**
     * Liste des pions blancs
     */
    private final List<Pawn> whitePawns = new ArrayList<>();

    /**
     * Indique si le mouvement précédent était un roque
     */
    private boolean didCastle = false;

    /**
     * Indique si le mouvement précédent était un en passant
     */
    private boolean didEnPassant = false;

    /**
     * Représente la pièce ayant bougé au dernier tour
     */
    private Piece lastMovedPiece;

    /**
     * Constructeur de la classe ChessBoard
     */
    public ChessBoard() {
        setPlayerBoard(PlayerColor.BLACK);
        setPlayerBoard(PlayerColor.WHITE);
    }

    /**
     * Enlève sur le plateau la pièce à la position donnée
     *
     * @param to Position de la pièce à enlever
     */
    public void remove(Vector to) {
        state[to.getX()][to.getY()] = null;
    }

    /**
     * Place une pièce à la position donnée
     *
     * @param piece Pièce à placer
     * @param to    Position de la pièce
     */
    public void put(Piece piece, Vector to) {
        int x = to.getX();
        int y = to.getY();
        state[x][y] = piece;
    }

    /**
     * Récupère la pièce à la position donnée
     *
     * @param from Position de la pièce
     * @return La pièce à la position donnée
     */
    public Piece getPiece(Vector from) {
        return checkPosition(from) ? state[from.getX()][from.getY()] : null;
    }

    /**
     * Indique si la position est libre
     *
     * @param from Position à vérifier
     * @return true si la position est libre, false sinon
     */
    public boolean isFree(Vector from) {
        return getPiece(from) == null;
    }

    /**
     * Place les pièces pour une couleur donnée
     *
     * @param color Couleur des pièces à placer
     */
    private void setPlayerBoard(PlayerColor color) {

        // Génère la ligne arrière
        int line = color == PlayerColor.BLACK ? BOARD_SIZE - 1 : 0;
        state[0][line] = new Rook(color, new Vector(0, line));
        state[1][line] = new Knight(color, new Vector(1, line));
        state[2][line] = new Bishop(color, new Vector(2, line));
        state[3][line] = new Queen(color, new Vector(3, line));
        state[4][line] = new King(color, new Vector(4, line));

        if (color == PlayerColor.WHITE) {
            whiteKing = state[4][line];
        } else {
            blackKing = state[4][line];
        }

        state[5][line] = new Bishop(color, new Vector(5, line));
        state[6][line] = new Knight(color, new Vector(6, line));
        state[7][line] = new Rook(color, new Vector(7, line));
        // Genère les pions de première ligne
        line = color == PlayerColor.BLACK ? BOARD_SIZE - 2 : 1;
        List<Pawn> pawns = _getPawns(color);
        for (int i = 0; i < BOARD_SIZE; ++i) {
            Pawn p = new Pawn(color, new Vector(i, line));
            state[i][line] = p;
            pawns.add(p);
        }
    }

    /**
     * Vérifie si la position donnée est valide (se situe sur le plateau)
     *
     * @param position Position à vérifier
     * @return true si la position est valide, false sinon
     */
    private boolean checkPosition(Vector position) {

        return !(position.getX() < 0 || position.getX() >= BOARD_SIZE ||
                position.getY() < 0 || position.getY() >= BOARD_SIZE);
    }

    /**
     * Récupère la dernière pièce bougée
     *
     * @return La dernière pièce bougée
     */
    public Piece getLastMovedPiece() {
        return lastMovedPiece;
    }

    /**
     * Défini la dernière pièce bougée
     *
     * @param piece Pièce bougée
     */
    public void setLastMovedPiece(Piece piece) {
        lastMovedPiece = piece;
    }

    /**
     * Récupère la liste des pions d'une couleur donnée
     *
     * @param color Couleur des pions à récupérer
     * @return La liste des pions d'une couleur donnée
     */
    private List<Pawn> _getPawns(PlayerColor color) {
        return color == PlayerColor.BLACK ? blackPawns : whitePawns;
    }

    /**
     * Permet de retourner une copie de la liste des pions d'une couleur donnée
     *
     * @param color Couleur des pions à récupérer
     * @return Copie de la liste de pions
     */
    public List<Pawn> getPawns(PlayerColor color) {
        return new ArrayList<>(_getPawns(color));
    }

    /**
     * Enlève un pion dans la liste des pions.
     *
     * @param color Couleur du pion à enlever
     * @param pawn  Pion à enlever
     */
    public void removePawn(PlayerColor color, Pawn pawn) {
        List<Pawn> pawns = _getPawns(color);
        pawns.remove(pawn);
    }

    /**
     * Retourne toutes les pièces pour une couleur donnée
     *
     * @param color Couleur des pièces à récupérer
     * @return Liste des pièces pour une couleur donnée
     */
    public List<Piece> getAllPiecesForAColor(PlayerColor color) {

        List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                Piece p = state[i][j];
                if (p != null && p.getColor() == color) {
                    pieces.add(p);
                }
            }
        }

        return new ArrayList<>(pieces);
    }

    /**
     * Vérifie si une case est en danger pour une couleur donnée.
     * On effectue tous les moves possibles des pièces opposées pour vérifier
     * si un des moves tombent sur la case donnée.
     *
     * @param position Position à vérifier
     * @param color    Couleur des pièces à vérifier
     * @return true si la case est en danger, false sinon
     */
    public boolean isThreatened(Vector position, PlayerColor color) {

        List<Piece> opponentPieces = getAllPiecesForAColor(color == PlayerColor.WHITE
                ? PlayerColor.BLACK : PlayerColor.WHITE);

        // On vérifie si une pièce adverse peut atteindre la position donnée
        for (Piece piece : opponentPieces) {
            for (Move move : piece.getLegalMoves()) {
                if (move.canCheck() && move.canMove(piece.getPosition(), position, this)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Vérifie si le roi de la couleur donnée est en échec
     *
     * @param color Couleur du roi
     * @return true si le roi est en échec, false sinon
     */
    public boolean isCheck(PlayerColor color) {

        Piece king = color == PlayerColor.WHITE ? whiteKing : blackKing;

        return isThreatened(king.getPosition(), color);
    }

    /**
     * Met à jour l'état de l'indicateur qui signale un roque
     *
     * @param didCastle Valeur de l'indicateur
     */
    public void setDidCastle(boolean didCastle) {
        this.didCastle = didCastle;
    }

    /**
     * Met à jour l'état de l'indicateur qui signale une prise en passant
     *
     * @param didEnPassant Valeur de l'indicateur
     */
    public void setDidEnPassant(boolean didEnPassant) {
        this.didEnPassant = didEnPassant;
    }

    /**
     * Récupère l'état de l'indicateur qui signale un roque
     *
     * @return Valeur de l'indicateur
     */
    public boolean getDidCastle() {
        return didCastle;
    }

    /**
     * Récupère l'état de l'indicateur qui signale une prise en passant
     *
     * @return Valeur de l'indicateur
     */
    public boolean getDidEnPassant() {
        return didEnPassant;
    }

    /**
     * Défini la pièce qui est impliquée dans un mouvement qui contient deux pièces.
     * On l'utilise lors d'un roque ou d'une prise en passant
     *
     * @param secondPieceInvolvedInLastMove Pièce impliquée dans le mouvement
     */
    public void setSecondPieceInvolvedInLastMove(Piece secondPieceInvolvedInLastMove) {
        this.secondPieceInvolvedInLastMove = secondPieceInvolvedInLastMove;
    }

    /**
     * Récupère la pièce qui est impliquée dans un mouvement qui contient deux pièces.
     * On l'utilise lors d'un roque ou d'une prise en passant
     *
     * @return Pièce impliquée dans le mouvement
     */
    public Piece getSecondPieceInvolvedInLastMove() {
        return secondPieceInvolvedInLastMove;
    }

    /**
     * Défini le tour du jeu
     *
     * @param turn Nouveau tour
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * Renvoie le tour actuel du jeu
     *
     * @return Tour actuel du jeu
     */
    public int getTurn() {
        return turn;
    }
}
