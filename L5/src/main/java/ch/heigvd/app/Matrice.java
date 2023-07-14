package ch.heigvd.app;

import ch.heigvd.app.operateur.Operateur;

/**
 * Classe qui représente une matrice d'entiers de taille M x N.
 * On peut réaliser des opérations sur ces matrices telles que l'addition,
 * la soustraction et la multiplication.
 *
 * @author Joris Decoppet
 * @author Hugo Ducommun
 * @author Alexis Martins
 *
 * Date : 11.11.2022
 */
public class Matrice {
    private final int[][] matrice;
    private final int modulo;

    /**
     * Constructeur de la classe Matrice qui génère des éléments aléatoires.
     *
     * @param x      nombre de lignes
     * @param y      nombre de colonnes
     * @param modulo modulo de la matrice
     * @throws RuntimeException Si les tailles ou le modulo sont plus petits que 1
     */
    public Matrice(int x, int y, int modulo) {
        if (x < 1 || y < 1 || modulo < 1) {
            throw new RuntimeException();
        }

        this.modulo = modulo;
        this.matrice = new int[x][y];
        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                this.matrice[i][j] = (int) (Math.random() * (modulo - 1));
            }
        }
    }

    /**
     * Constructeur de la classe Matrice lorsque l'on donne un tableau
     * d'entier représentant les composantes
     *
     * @param m      matrice avec modulo corrigé su besoin, puis affectée à
     *               l'attribut matrice
     * @param modulo modulo de la matrice
     * @throws RuntimeException si la matrice est vide ou que le modulo
     * est plus petit que 1.
     */
    public Matrice(int[][] m, int modulo) {

        if (m == null || m.length == 0 || m[0].length == 0 || modulo < 1) {
            throw new RuntimeException();
        }

        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m[0].length; ++j) {
                m[i][j] = Math.floorMod(m[i][j], modulo);
            }
        }
        this.modulo = modulo;
        this.matrice = m;
    }

    /**
     * @return Taille X de la matrice
     */
    public int getTailleX() {

        return matrice.length;
    }

    /**
     * @return Taille Y de la matrice
     */
    public int getTailleY() {

        return matrice[0].length;
    }

    /**
     * @param x Indice X
     * @param y Indice Y
     * @return Elément à la position x, y de la matrice
     * @throws RuntimeException Si les indices sont hors de la portée
     */
    public int getElement(int x, int y) {

        if (x < 0 || x >= matrice.length || y < 0 || y >= matrice[0].length) {
            throw new RuntimeException();
        }
        return matrice[x][y];
    }

    /**
     * Fonction pour faire un calcul avec une matrice selon l'opérateur passé
     * en paramètre.
     *
     * @param m  matrice pour le calcul
     * @param op opérateur pour le calcul
     * @return matrice résultat du calcul
     * @throws RuntimeException Si la matrice fournie en paramètre est
     * valide et de même modulo
     */
    public Matrice calculer(Matrice m, Operateur op) {

        if (m == null || this.modulo != m.modulo) {
            throw new RuntimeException();
        }

        // Calcul de la taille X et Y de la matrice finale
        int x = Math.max(this.matrice.length, m.matrice.length);
        int y = Math.max(this.matrice[0].length, m.matrice[0].length);

        // Faire le pour chaque composante selon l'opérateur passé en paramètre
        int[][] ret = new int[x][y];
        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {

                // Si la matrice ne possède pas de composante pour la case
                // donnée, alors la valeur retournée est 0
                int i1 = (i < this.matrice.length && j < this.matrice[0].length)
                         ? this.matrice[i][j] : 0;
                int i2 = (i < m.matrice.length && j < m.matrice[0].length)
                         ? m.matrice[i][j] : 0;

                ret[i][j] = Math.floorMod(op.calculer(i1, i2), this.modulo);
            }
        }
        return new Matrice(ret, this.modulo);
    }

    /**
     * Affiche les éléments de la matrice.
     *
     * @return Chaine de caractère contenant les composantes de la matrice
     */
    public String toString() {

        StringBuilder str = new StringBuilder();

        for (int[] ints : this.matrice) {
            for (int anInt : ints) {

                str.append(anInt).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }
}
