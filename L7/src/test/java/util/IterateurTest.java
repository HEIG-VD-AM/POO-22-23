package util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Classe de tests de la classe Iterateur.
 * @author Decoppet Joris, Ducommun Hugo, Martins Alexis
 */
public class IterateurTest 
{
    /**
     * Vérifie que la méthode suivant des itérateurs pase bien à l'élément suivant
     * en retournant l'élément courant.
     */
    @Test
    public void obtenirSuivantIterateur() {
        Pile p = new Pile();
        p.empiler(5);
        p.empiler(7);
        p.empiler(9);
        Iterateur it = p.iterateur();
        assertEquals(9, it.suivant());
        assertEquals(7, it.suivant());
        assertEquals(5, it.suivant());
    }

    /**
     * S'il n'y a plus de suivant, alors une exception devrait être levée.
     * La méthode possedeSuivant retourne false pour cela.
     */
    @Test
    public void obtenirSuivantVideIterateur() {
        Pile p = new Pile();
        Iterateur it = p.iterateur();
        assertThrows(NullPointerException.class, ()->it.suivant());
    }

    /**
     * Vérifie que la méthode possedeSuivant retourne true lorsqu'il y a un
     * élément suivant.
     */
    @Test
    public void aUnSuivantIterateur() {
        Pile p = new Pile();
        p.empiler(3);
        p.empiler(4);
        Iterateur it = p.iterateur();
        assertTrue(it.possedeSuivant());
    }
}