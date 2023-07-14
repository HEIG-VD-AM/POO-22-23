package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PlayerColor;
import engine.pieces.*;
import engine.utils.Vector;

import java.util.List;

/**
 * Classe représentant la logique du jeu et s'interfaçant
 * avec la vue.
 *
 * @author Annen Rayane
 * @author Decoppet Joris
 * @author Ducommun Hugo
 * @author Martins Alexis
 */
public class Controller implements ChessController {
    /**
     * Vue affichant l'état du jeu et permettant
     * de déplacer les pièces.
     */
    private ChessView view;
    /**
     * Plateau
     */
    private ChessBoard board;

    /**
     * Indique si la partie est terminée
     */
    private boolean gameEnded = false;

    /**
     * Démarrer une nouvelle partie
     *
     * @param view la vue à utiliser
     */
    @Override
    public void start(ChessView view) {
        this.view = view;
        this.view.startView();
        newGame();
    }

    /**
     * Passe au tour suivant
     *
     * @param board plateau
     * @param piece pièce qui a été bougée sur le tour courant
     */
    private void nextTurn(ChessBoard board, Piece piece) {
        board.setLastMovedPiece(piece);
        board.setTurn(board.getTurn() + 1);
    }

    /**
     * @return le joueur courant
     */
    public PlayerColor getCurrentPlayer() {
        return board.getTurn() % 2 == 0 ? PlayerColor.WHITE : PlayerColor.BLACK;
    }

    /**
     * Indique si un mouvement peut être effectué et mets à jour l'interface en fonction de la
     * situation en jeu (échec, échec et mat, échec par pat, promotion, grand et petit roque)
     *
     * @param fromX position initiale en X
     * @param fromY position initiale en Y
     * @param toX   position finale en X
     * @param toY   positiin finale en Y
     * @return si oui ou non le mouvement a été effectué
     */
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {

        if (gameEnded) {
            view.displayMessage("Game ended! You must restart a new game to play!");
            return false;
        }

        Vector from = new Vector(fromX, fromY);
        Vector to = new Vector(toX, toY);
        Piece pieceMoved = board.getPiece(from);

        if (pieceMoved == null) return false;

        // À la position finale, on trouve potentiellement une pièce capturée
        Piece capturedPiece = board.getPiece(to);

        // On gère les cas où ce ne serait pas le bon joueur qui se déplace
        if (pieceMoved.getColor() != getCurrentPlayer()) {
            view.displayMessage("It's not your turn!");
            return false;
        }

        // On vérifie en premier lieu si mouvement est possible avec la pièce qui est déplacée.
        if (pieceMoved.canMove(to, board)) {
            if (capturedPiece != null) {
                view.displayMessage(capturedPiece + " captured!");
            }

            // On récupère la pièce si une prise en passant est effectuée
            if (board.getDidEnPassant()) {
                capturedPiece = board.getSecondPieceInvolvedInLastMove();
                board.setDidEnPassant(false);
                board.setSecondPieceInvolvedInLastMove(null);
            }

            // On applique les mouvements sur le plateau et on met à jour la position de la pièce déplacée
            // sur le plateau
            commit(view, pieceMoved, capturedPiece, to, from);
            commit(board, pieceMoved, capturedPiece, to);

            pieceMoved.setPosition(to);
            boolean hasMovedBefore = pieceMoved.getFirstTurnMove() != -1;
            if(!hasMovedBefore) {
                pieceMoved.setFirstTurnMove(board.getTurn());
            }

            // Si le joueur est en échec, on défait le mouvement effectué et on remet à l'état initial
            // la pièce bougée et éventuellement celle capturée
            if (board.isCheck(getCurrentPlayer())) {
                revert(board, pieceMoved, capturedPiece, to, from);
                revert(view, pieceMoved, capturedPiece, to, from);

                pieceMoved.setPosition(from);
                if (!hasMovedBefore) {
                    pieceMoved.setFirstTurnMove(-1);
                }

                view.displayMessage("Check!");
                return false;
            }

            // On gère la promotion
            promotion();

            // On vérifie le petit et le grand roque
            if (board.getDidCastle()) {
                castling(pieceMoved);
            }

            // On vérifie l'échec et mat, l'échec par pat et matériel insuffisant.
            if (endGameConditions()) {
                return false;
            }

            // On passe au tour suivant
            nextTurn(board, pieceMoved);
            return true;
        }

        return false;
    }

    /**
     * Gestion de l'égalité insuffisance de matériel. L'insuffisance de matériel
     * se déclenche lorsque les deux joueurs n'ont plus que un roi et/ou un cavalier et/ou un fou.
     * Chaque de ces pièces ne devant être qu'en un seul exemplaire maximum.
     *
     * @return true si la partie est terminée, false sinon
     * @see <a href="https://fr.wikipedia.org/wiki/Nulle">Source pour l'insuffisance de matériel</a>
     */
    private boolean insufficientMaterial() {

        int nbWhiteBishop = 0;
        int nbWhiteKnight = 0;
        int nbBlackBishop = 0;
        int nbBlackKnight = 0;

        for (int i = 0; i < ChessBoard.BOARD_SIZE; ++i) {
            for (int j = 0; j < ChessBoard.BOARD_SIZE; ++j) {

                Piece piece = board.getPiece(new Vector(i, j));
                if (piece == null) continue;

                // Vérifie qu'il n'y ait qu'un seul roi de chaque couleur
                if (!(piece instanceof Knight) && !(piece instanceof Bishop) && !(piece instanceof King)) {
                    return false;
                }

                if (piece instanceof Knight) {
                    if (piece.getColor() == PlayerColor.WHITE) {
                        ++nbWhiteKnight;
                    } else {
                        ++nbBlackKnight;
                    }
                } else if (piece instanceof Bishop) {
                    if (piece.getColor() == PlayerColor.WHITE) {
                        ++nbWhiteBishop;
                    } else {
                        ++nbBlackBishop;
                    }
                }
            }
        }

        return nbWhiteBishop <= 1 && nbBlackBishop <= 1 && nbWhiteKnight <= 1 && nbBlackKnight <= 1;
    }

    /**
     * Gère les conditions de fin de jeu : échec par pat et échec et mat.
     *
     * @return si oui ou non on remplit une des conditions de fin de jeu.
     */
    private boolean endGameConditions() {
        PlayerColor opponentColor = getCurrentPlayer() == PlayerColor.WHITE ?
                PlayerColor.BLACK : PlayerColor.WHITE;

        boolean hasNoLegalMoves = noPossibleLegalMoves(opponentColor);

        board.setDidEnPassant(false);
        board.setDidCastle(false);
        board.setSecondPieceInvolvedInLastMove(null);

        if (board.isCheck(opponentColor) && hasNoLegalMoves) { // checkmate
            view.displayMessage("Checkmate! The winner is: " + getCurrentPlayer());
            endGame();
            return true;
        } else if (!board.isCheck(opponentColor) && hasNoLegalMoves) { //stalemate
            view.displayMessage("Draw by stalemate!");
            endGame();
            return true;
        } else if (insufficientMaterial()) {    // Insufficient material
            view.displayMessage("Draw by insufficient material!");
            endGame();
            return true;
        }

        return false;
    }

    /**
     * Gère le petit et le grand roque
     *
     * @param piece le roi impliqué dans le roque
     */
    private void castling(Piece piece) {
        Piece rook = board.getSecondPieceInvolvedInLastMove();
        Vector newPosition;
        // Calcul de la position de la nouvelle tour,
        // fonctionne pour le petit et le grand roque
        if (rook.getPosition().getX() - piece.getPosition().getX() < 0) {
            newPosition = (new Vector(piece)).add(new Vector(1, 0));
        } else {
            newPosition = (new Vector(piece)).add(new Vector(-1, 0));
        }
        commit(view, rook, null, newPosition, rook.getPosition());
        commit(board, rook, null, newPosition);
        rook.setPosition(newPosition);
        rook.setFirstTurnMove(board.getTurn());
        board.setDidCastle(false);
        board.setSecondPieceInvolvedInLastMove(null);
    }

    /**
     * Gère la promotion et dans le cas où elle aurait lieu, demande la
     * nouvelle pièce qui doit remplacer le pion.
     */
    private void promotion() {
        Piece[] possibilities = {
                new Queen(getCurrentPlayer(), new Vector(0, 0)),
                new Bishop(getCurrentPlayer(), new Vector(0, 0)),
                new Rook(getCurrentPlayer(), new Vector(0, 0)),
                new Knight(getCurrentPlayer(), new Vector(0, 0))
        };

        for (Pawn pawn : board.getPawns(getCurrentPlayer())) {
            if (pawn.checkPromotion()) {
                Piece newPiece = null;
                while (newPiece == null) {
                    newPiece = view.askUser("Promotion!", "Choose a piece", possibilities);
                    commit(view, newPiece, pawn, pawn.getPosition(), pawn.getPosition());
                    commit(board, newPiece, pawn, pawn.getPosition());
                    newPiece.setPosition(pawn.getPosition());
                    newPiece.setFirstTurnMove(board.getTurn());
                    board.removePawn(getCurrentPlayer(), pawn);
                }
            }
        }
    }

    /**
     * Vérifie que le joueur n'a plus de mouvements légaux.
     *
     * @return si oui ou non le joueur courant peut effectuer des mouvements.
     */
    private boolean noPossibleLegalMoves(PlayerColor color) {
        List<Piece> friendlyPieces = board.getAllPiecesForAColor(color);
        for (int i = 0; i < ChessBoard.BOARD_SIZE; ++i) {
            for (int j = 0; j < ChessBoard.BOARD_SIZE; ++j) {
                for (Piece piece : friendlyPieces) {
                    Vector to = new Vector(i, j);
                    Vector from = piece.getPosition();
                    if (!to.equals(from) && piece.canMove(to, board)) {
                        // On génère le mouvement, on vérifie si le roi est toujours en échec, si ce n'est pas le
                        // cas, alors ce n'est pas un échec et mat. Dans tous les cas, on défait le mouvement.
                        Piece potentiallyCapturedPiece = board.getPiece(to);
                        commit(board, piece, potentiallyCapturedPiece, to);
                        piece.setPosition(to);
                        if (!board.isCheck(color)) {
                            revert(board, piece, potentiallyCapturedPiece, to, from);
                            piece.setPosition(from);
                            return false;
                        }
                        revert(board, piece, potentiallyCapturedPiece, to, from);
                        piece.setPosition(from);
                    }
                }
            }
        }

        return true;
    }

    /**
     * Applique un mouvement sur la vue
     *
     * @param view          la vue impliquée
     * @param pieceMoved    la pièce qui a été bougée
     * @param capturedPiece l'éventuelle pièce capturée
     * @param to            la position de départ
     * @param from          la position d'arrivée
     */
    private void commit(ChessView view,
                        Piece pieceMoved,
                        Piece capturedPiece,
                        Vector to,
                        Vector from) {
        if (capturedPiece != null) {
            Vector pos = capturedPiece.getPosition();
            view.removePiece(pos.getX(), pos.getY());
        }
        view.removePiece(from.getX(), from.getY());
        view.putPiece(pieceMoved.getType(), pieceMoved.getColor(), to.getX(), to.getY());
    }

    /**
     * Applique un mouvement sur le plateau
     *
     * @param board         le plateau impliqué
     * @param pieceMoved    la pièce qui a été bougée
     * @param capturedPiece l'éventuelle pièce capturée
     * @param to            la position d'arrivée
     */
    private void commit(ChessBoard board,
                        Piece pieceMoved,
                        Piece capturedPiece,
                        Vector to) {
        if (capturedPiece != null) {
            board.remove(capturedPiece.getPosition());
        }
        board.remove(pieceMoved.getPosition());
        board.put(pieceMoved, to);
    }

    /**
     * Défait un mouvement sur le plateau
     *
     * @param board         le plateau impliqué
     * @param pieceMoved    la pièce qui a été bougée
     * @param capturedPiece l'éventuelle pièce capturée
     * @param to            la position de départ
     * @param from          la position d'arrivée
     */
    private void revert(ChessBoard board,
                        Piece pieceMoved,
                        Piece capturedPiece,
                        Vector to,
                        Vector from) {
        board.remove(to);
        board.put(pieceMoved, from);
        if (capturedPiece != null) {
            board.put(capturedPiece, to);
        }
    }

    /**
     * Défait un mouvement sur la vue
     *
     * @param view          la vue impliquée
     * @param pieceMoved    la pièce qui a été bougée
     * @param capturedPiece l'éventuelle pièce capturée
     * @param to            la position de départ
     * @param from          la position d'arrivée
     */
    private void revert(ChessView view,
                        Piece pieceMoved,
                        Piece capturedPiece,
                        Vector to,
                        Vector from) {
        view.removePiece(to.getX(), to.getY());
        view.putPiece(pieceMoved.getType(), pieceMoved.getColor(), from.getX(), from.getY());
        if (capturedPiece != null) {
            view.putPiece(capturedPiece.getType(), capturedPiece.getColor(), to.getX(), to.getY());
        }
    }

    /**
     * Créer une nouvelle partie
     */
    @Override
    public void newGame() {

        gameEnded = false;
        board = new ChessBoard();
        board.setTurn(0);

        for (int i = 0; i < ChessBoard.BOARD_SIZE; ++i) {
            for (int j = 0; j < ChessBoard.BOARD_SIZE; ++j) {
                view.removePiece(i, j);
                Piece p = board.getPiece(new Vector(i, j));
                if (p != null) {
                    view.putPiece(p.getType(), p.getColor(), i, j);
                }
            }
        }
    }

    /**
     * Termine la partie en cours
     */
    public void endGame() {
        gameEnded = true;
    }
}
