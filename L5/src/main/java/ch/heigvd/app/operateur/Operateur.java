package ch.heigvd.app.operateur;

/**
 * Classe abstraite qui représente une opération entre deux entiers.
 *
 * @author Joris Decoppet
 * @author Hugo Ducommun
 * @author Alexis Martins
 *
 * Date : 11.11.2022
 */
public abstract class Operateur {

    /**
     *
     * @param i1 Premier opérande
     * @param i2 Second opérande
     * @return   Résultat de l'opération des deux opérandes.
     *           L'opération est tirée d'une des sous-classes.
     */
    public abstract int calculer(int i1, int i2);
}
