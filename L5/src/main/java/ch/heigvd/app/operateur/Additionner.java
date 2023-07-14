package ch.heigvd.app.operateur;

/**
 * Classe qui représente l'opération d'addition entre deux entiers.
 *
 * @author Joris Decoppet
 * @author Hugo Ducommun
 * @author Alexis Martins
 *
 * Date : 11.11.2022
 */
public class Additionner extends Operateur {
    public int calculer(int i1, int i2) {
        return i1 + i2;
    }
}