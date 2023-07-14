package util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

/**
 * Classe de tests de la classe Pile.
 * @author Decoppet Joris, Ducommun Hugo, Martins Alexis
 */
public class PileTest 
{
    /**
     * Vérifie que la pile vide est indiquée comme telle.
     */
    @Test
    public void pileVide(){
        Pile p = new Pile();
        assertTrue(p.estVide());
    }

    /**
     * Retirer un élément d'une pile vide provoque une erreur
     */
    @Test
    public void desempilerPileVide(){
        Pile p = new Pile();
        assertThrows(NoSuchElementException.class, ()->p.desempiler());
    }

    /**
     * Vérifie que la méthode retournant la taille donne bien la bonne taille.
     */
    @Test
    public void pileCompterElement(){
        Pile p = new Pile();
        p.empiler(3);
        p.empiler(3);
        p.empiler(3);
        assertEquals(3, p.obtenirTaille());
    }

    /**
     * Vérifie que les objets sont empilés, puis dépilés dans l'ordre.
     */
    @Test
    public void pileEnleverElement(){
        Pile p = new Pile();
        p.empiler(3);
        p.empiler(5);
        p.empiler(7);
        assertEquals(7, p.desempiler());
        assertEquals(5, p.desempiler());
        assertEquals(3, p.desempiler());
    }

    /**
     * Vérifie que l'affichage d'une pile se fait correctement
     */
    @Test
    public void pileAfficher(){
        Pile p = new Pile();
        p.empiler(3);
        p.empiler(5);
        p.empiler(7);
        assertEquals("[ <7> <5> <3> ]", p.toString());
    }

    /**
     * Vérifie que l'état de la pile est correct
     */
    @Test
    public void pileStatus(){
        Pile p = new Pile();
        p.empiler(3);
        p.empiler(5);
        p.empiler(7);
        assertEquals(7, p.etatPile()[0]);
        assertEquals(5, p.etatPile()[1]);
        assertEquals(3, p.etatPile()[2]);
    }

}
