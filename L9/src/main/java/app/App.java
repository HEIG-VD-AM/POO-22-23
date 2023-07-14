package app;

/**
 * App.java
 * @author Leandro Saraiva Maia
 * @author Hugo Ducommun
 * @author Joris Décoppet
 * @author Alexis Martins
 */
class LotR {
    public LotR() {
        Personne frodo = new Personne("Frodon", Lieu.Comte);
        Ennemi sauron = new Ennemi("Sauron", Lieu.Destin);

        System.out.println("-1-");
        Anneau anneau = sauron.anneauUnique();

        System.out.println("-2-");
        anneau.utiliser();

        System.out.println("-3-");
        anneau.definirProprietaire(frodo);

        System.out.println("-4-");
        anneau.utiliser();

        System.out.println("-5-");
        anneau.detruire();

        System.out.println("-6-");
        frodo.deplacer(Lieu.Destin);

        System.out.println("-7-");
        anneau.detruire();

        System.out.println("---");
    }

    public static void main(String... args) {
        new LotR();
        System.gc();
    }
}