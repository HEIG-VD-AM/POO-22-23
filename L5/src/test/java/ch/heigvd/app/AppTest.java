package ch.heigvd.app;

import static org.junit.Assert.*;

import ch.heigvd.app.operateur.*;
import org.junit.Test;

/**
 * Classe de tests de la classe Matrice et de toutes ses opérations et méthodes.
 *
 * @author Joris Decoppet
 * @author Hugo Ducommun
 * @author Alexis Martins
 *
 * Date : 11.11.2022
 */
public class AppTest {

    /**
     * Lors de la création d'une matrice avec des composantes aléatoires,
     * on teste que les tailles soient bonnes et que
     * le module des composantes soit bon.
     */
    @Test
    public void testCreationMatriceAleatoire() {
        int x = 2, y = 3, modulo = 4;
        Matrice m1 = new Matrice(x, y, modulo);

        boolean elementsValides = true;
        for (int a = 0; a < x; ++a) {
            for (int b = 0; b < x; ++b) {
                if (m1.getElement(a, b) >= modulo || m1.getElement(a, b) < 0) {
                    elementsValides = false;
                    break;
                }
            }
        }
        assertTrue(m1.getTailleX() == 2 && m1.getTailleY() == 3 && elementsValides);
    }

    /**
     * Lors de la création d'une matrice avec des composantes données,
     * il faut vérifier que celle-ci s'effectue
     * correctement (modulo s'applique correctement).
     */
    @Test
    public void testCreationMatriceFixe() {
        Matrice m1 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 5);
        assertEquals("1 3 1 1 \n3 2 4 2 \n1 0 1 0 \n", m1.toString());
    }

    /**
     * Passage d'un tableau de composantes null.
     */
    @Test(expected = RuntimeException.class)
    public void testMatriceNull() {
        Matrice m1 = new Matrice(null, 5);
    }

    /**
     * On vérifie que l'addition de deux matrices de même taille donne
     * le bon résultat.
     */
    @Test
    public void testMatriceAdditionMemeTaille() {
        Matrice m1 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 5);
        Matrice m2 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 5);

        assertEquals("2 1 2 2 \n1 4 3 4 \n2 0 2 0 \n",
                     m1.calculer(m2, new Additionner()).toString());
    }

    /**
     * On vérifie que l'addition de deux matrices de taille différente donne
     * le bon résultat. On teste notamment que la mise à niveau des tailles
     * n'impacte pas le résultat.
     */
    @Test
    public void testMatriceAdditionTailleDifferente() {
        Matrice m1 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 5);
        Matrice m2 = new Matrice(
                     new int[][]{{1, 4, 2, 3, 2}, {0, 1, 0, 4, 2}, {0, 0, 2, 0, 2}}, 5);

        assertEquals("2 2 3 4 2 \n3 3 4 1 2 \n1 0 3 0 2 \n",
                     m1.calculer(m2, new Additionner()).toString());
    }

    /**
     * On vérifie que la soustraction de deux matrices de même taille donne
     * le bon résultat.
     */
    @Test
    public void testMatriceSoustractionMemeTaille() {
        Matrice m1 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 5);
        Matrice m2 = new Matrice(
                     new int[][]{{1, 2, 4, 1}, {2, 2, 3, 4}, {2, 0, 2, 0}}, 5);

        assertEquals("0 1 2 0 \n1 0 1 3 \n4 0 4 0 \n",
                     m1.calculer(m2, new Soustraire()).toString());
    }

    /**
     * On vérifie que la soustraction de deux matrices de taille différente donne
     * le bon résultat. On teste notamment que la mise à niveau des tailles
     * n'impacte pas le résultat.
     */
    @Test
    public void testMatriceSoustractionTailleDifferente() {
        Matrice m1 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 5);
        Matrice m2 = new Matrice(
                     new int[][]{{1, 4, 2, 3, 2}, {0, 1, 0, 4, 2}, {0, 0, 2, 0, 2}}, 5);

        assertEquals("0 4 4 3 3 \n3 1 4 3 3 \n1 0 4 0 3 \n",
                     m1.calculer(m2, new Soustraire()).toString());
    }

    /**
     * On vérifie que la multiplication de deux matrices de même taille donne
     * le bon résultat.
     */
    @Test
    public void testMatriceMultiplicationMemeTaille() {
        Matrice m1 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 5);
        Matrice m2 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 5);

        assertEquals("1 4 1 1 \n4 4 1 4 \n1 0 1 0 \n",
                     m1.calculer(m2, new Multiplier()).toString());
    }

    /**
     * On vérifie que la multiplication de deux matrices de taille différente donne
     * le bon résultat. On teste notamment que la mise à niveau des tailles
     * n'impacte pas le résultat.
     */
    @Test
    public void testMatriceMultiplicationTailleDifferente() {
        Matrice m1 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 5);
        Matrice m2 = new Matrice(
                     new int[][]{{1, 4, 2, 3, 2}, {0, 1, 0, 4, 2}, {0, 0, 2, 0, 2}}, 5);

        assertEquals("1 2 2 3 0 \n0 2 0 3 0 \n0 0 2 0 0 \n",
                     m1.calculer(m2, new Multiplier()).toString());
    }

    /**
     * L'addition de matrices avec des modulos différents n'est pas possible.
     * Cela nous génère une erreur.
     */
    @Test(expected = RuntimeException.class)
    public void testMatriceAdditionDifferentModulo() {
        Matrice m1 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 4);
        Matrice m2 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 5);
        m1.calculer(m2, new Additionner());
    }

    /**
     * La soustraction de matrices avec des modulos différents n'est pas possible.
     * Cela nous génère une erreur.
     */
    @Test(expected = RuntimeException.class)
    public void testMatriceSoustraireDifferentModulo() {
        Matrice m1 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 4);
        Matrice m2 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 5);
        m1.calculer(m2, new Soustraire());
    }

    /**
     * La multiplication de matrices avec des modulos différents n'est pas possible.
     * Cela nous génère une erreur.
     */
    @Test(expected = RuntimeException.class)
    public void testMatriceMultiplierDifferentModulo() {
        Matrice m1 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 4);
        Matrice m2 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 5);
        m1.calculer(m2, new Multiplier());
    }

    /**
     * L'addition avec une matrice null génère une erreur
     */
    @Test(expected = RuntimeException.class)
    public void testAdditionAvecMatriceNull() {
        Matrice m1 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 4);
        m1.calculer(null, new Additionner());
    }

    /**
     * La soustraction avec une matrice null génère une erreur
     */
    @Test(expected = RuntimeException.class)
    public void testSoustractionAvecMatriceNull() {
        Matrice m1 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 4);
        m1.calculer(null, new Soustraire());
    }

    /**
     * La multiplication avec une matrice null génère une erreur
     */
    @Test(expected = RuntimeException.class)
    public void testMultiplicationAvecMatriceNull() {
        Matrice m1 = new Matrice(
                     new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 4);
        m1.calculer(null, new Multiplier());
    }

    /**
     * La cération d'une matrice en passant un tableau de composantes,
     * mais avec un modulo négatif génère une erreur.
     */
    @Test(expected = RuntimeException.class)
    public void testMatriceModuloZero() {
        new Matrice(new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 0);
    }

    /**
     * La création d'une matrice dont le tableau de composante a une
     * taille X inférieure à 1 génère une erreur.
     */
    @Test(expected = RuntimeException.class)
    public void testMatriceTailleXZero() {
        new Matrice(new int[][]{{}}, 5);
    }

    /**
     * La taille Y d'une matrice doit être strictement positive.
     */
    @Test(expected = RuntimeException.class)
    public void testMatriceTailleYZero() {
        new Matrice(new int[][]{{}}, 5);
    }

    /**
     * La création d'une matrice aléatoire avec un modulo incorrect génère
     * une erreur.
     */
    @Test(expected = RuntimeException.class)
    public void testMatriceAleatoireModuloZero() {
        new Matrice(2, 4, 0);
    }

    /**
     * La création d'une matrice aléatoire avec une taille X incorrecte génère
     * une erreur.
     */
    @Test(expected = RuntimeException.class)
    public void testMatriceAleatoireTailleXZero() {
        new Matrice(0, 2, 5);
    }

    /**
     * La création d'une matrice aléatoire avec une taille incorrecte génère
     * une erreur.
     */
    @Test(expected = RuntimeException.class)
    public void testMatriceAleatoireTailleYZero() {
        new Matrice(2, 0, 5);
    }
}
