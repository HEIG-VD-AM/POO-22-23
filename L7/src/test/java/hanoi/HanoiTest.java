package hanoi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Classe de tests de la classe Hanoi.
 * @author Decoppet Joris, Ducommun Hugo, Martins Alexis
 */
public class HanoiTest
{
    /**
     * Vérifie que le statut des tours est correct.
     */
    @Test
    public void statusHanoiTest() {
        Hanoi h = new Hanoi(3);
        int[][] status = h.status();
        assertTrue(status[0][0] == 1 && status[0][1] == 2 && status[0][2] == 3 && status[0].length == 3 &&
                   status[1].length == 0 &&
                   status[2].length == 0);
    }

    /**
     * Vérifie que le programme se termine bien correctement.
     */
    @Test
    public void finishedHanoiTest() {
        Hanoi h = new Hanoi(3);
        h.solve();
        assertTrue(h.finished());
    }

    /**
     * Vérifie que le jeu n'est pas forcément résolu.
     */
    @Test
    public void notFinishedHanoiTest() {
        Hanoi h = new Hanoi(3);
        assertFalse(h.finished());
    }

    /**
     * On vérifie que le programme soit résolu correctement et que tous les éléments soient au bon endroit.
     */
    @Test
    public void solveHanoiTest() {
        Hanoi h = new Hanoi(3);
        h.solve();
        int[][] status = h.status();
        assertTrue(status[0].length == 0 &&
                   status[1].length == 0 &&
                   status[2][0] == 1 && status[2][1] == 2 && status[2][2] == 3 && status[2].length == 3);
    }
}
