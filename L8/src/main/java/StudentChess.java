import chess.ChessController;
import chess.ChessView;
import chess.views.console.ConsoleView;
import engine.Controller;
import chess.views.gui.GUIView;

/**
 * Jeu d'échecs
 */
public class StudentChess
{
    public static void main( String[] args )
    {
        ChessController controller = new Controller();
        ChessView view = new GUIView(controller);
        //ChessView view = new ConsoleView(controller);
        controller.start(view);
    }
}