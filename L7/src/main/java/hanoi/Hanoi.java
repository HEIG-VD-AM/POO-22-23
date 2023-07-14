package hanoi;

import util.Pile;

/**
 * Cette classe représente le jeu des tours d'Hanoi. Elle modélise les trois
 * aiguilles du jeu, ainsi que les disques qui sont dessus.
 * Elle permet aussi de résoudre le problème et d'afficher les résultats
 * via un affichage console ou graphique de la classe HanoiDisplayer.
 * @author Decoppet Joris, Ducommun Hugo, Martins Alexis
 */
public class Hanoi {
    // Nombres d'aiguilles du jeu d'Hanoi
    private static final int NB_AIGUILLES = 3;
    // Nombre de disques du jeu
    private final int nbDisques;
    // Nombre de tours réalisés à un instant t
    private int nbTours;
    // Représentation du jeu
    private final Pile[] aiguilles;
    // Afficheur du jeu
    private final HanoiDisplayer displayer;

    /**
     * Constructeur de la classe Hanoi.
     * @param disques le nombre de disques à utiliser
     * @param displayer l'afficheur à utiliser
     * @throws IllegalArgumentException Si le nombre de disques est inférieur à 1
     */
    public Hanoi(int disques, HanoiDisplayer displayer) {

        if(disques < 1) {
            throw new IllegalArgumentException("Le nombre de disques doit être " +
                                               "un entier positif");
        }

        this.nbDisques = disques;
        this.nbTours = 0;
        this.displayer = displayer;
        this.aiguilles = new Pile[NB_AIGUILLES];

        // Initialisation des aiguilles
        for (int i = 0; i < NB_AIGUILLES; ++i) {
            this.aiguilles[i] = new Pile();
        }

        // On empile les disques sur la première aiguille
        for (int i = this.nbDisques; i > 0; i--) {
            this.aiguilles[0].empiler(i);
        }
    }

    /**
     * Constructeurprenant uniquement les disques en paramètre.
     * @param disques Nombre de disques
     */
    public Hanoi(int disques) {

        this(disques, new HanoiDisplayer());
    }

    /**
     * Résoud le problème des tours d'Hanoi Appel à la fonction récursive.
     */
    public void solve() {

        this.displayer.display(this);
        algorithmeDeResolution(this.nbDisques, this.aiguilles[0],
                               this.aiguilles[1], this.aiguilles[2]);
    }

    /**
     * Algorithme récursif de résolution du problème des tours d'Hanoi.
     * @param nbDisques Nombre de disques sur l'aiguille
     * @param depart Aiguille de départ
     * @param intermediaire Aiguille intermédiaire
     * @param arrivee Aiguille d'arrivée
     */
    private void algorithmeDeResolution(int nbDisques, Pile depart,
                                        Pile intermediaire, Pile arrivee){
        // Cas trivial dans lequel on déplace juste 1 disque
        if (nbDisques == 1) {
            deplacerDisque(depart, arrivee);
        } else {
            // Algorithme d'Hanoi récursif
            algorithmeDeResolution(nbDisques - 1, depart, arrivee, intermediaire);
            deplacerDisque(depart, arrivee);
            algorithmeDeResolution(nbDisques - 1, intermediaire, depart, arrivee);
        }
    }

    /**
     * Déplace un disque d'une aiguille à une autre et incrémente
     * le nombre de tours.
     * @param depart Aiguille de départ
     * @param arrivee Aiguille d'arrivée
     */
    private void deplacerDisque(Pile depart, Pile arrivee) {
        arrivee.empiler(depart.desempiler());
        ++nbTours;
        this.displayer.display(this);
    }

    /**
     * Retourne l'état de jeu d'Hanoi sous forme de tableau bidimensionnel.
     * @return Tableau bidimensionnel représentant l'état du jeu.
     */
    public int[][] status() {

        int[][] ret = new int[this.aiguilles.length][];

        for (int i = 0; i < NB_AIGUILLES; ++i) {
            // Récuoération du tableau 1D d'une aiguille
            Object[] etat = this.aiguilles[i].etatPile();

            // Création d'un tableau de la taille de l'aiguille
            ret[i] = new int[etat.length];

            // Affectation du tableau case par case
            for (int j = 0; j < etat.length; ++j) {
                ret[i][j] = (int) etat[j];
            }
        }

        return ret;
    }

    /**
     * Indique si le jeu s'est terminé en vérifiant que tous les disques
     * nécessaires sont sur la dernière aiguille.
     * @return  Vrai --> Jeu fini
     *          Faux --> Jeu non fini
     */
    public boolean finished() {
        return this.aiguilles[NB_AIGUILLES - 1].obtenirTaille() == this.nbDisques;
    }

    /**
     * Retourne le nombre de tours effectuées
     * @return Nombre de tours effectuées
     */
    public int turn() {

        return this.nbTours;
    }

    public String toString() {

        // En-têtes des aiguilles lors de l'affichage
        final String[] enTetes = {"One:", "Two:", "Three:" };
        StringBuilder sb = new StringBuilder();

        sb.append("-- Turn: ");
        sb.append(this.nbTours);
        sb.append("\n");

        // Affichage des piles représentant l'aiguille
        for(int i = 0; i < NB_AIGUILLES; ++i) {
            sb.append(String.format("%-7s", enTetes[i]));
            sb.append(this.aiguilles[i]);
            sb.append("\n");
        }

        return sb.toString();
    }
}
