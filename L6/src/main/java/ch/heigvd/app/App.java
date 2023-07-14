package ch.heigvd.app;

/**
 * Programme principal qui permet de valider les principes généraux du système d'horaires
 * implémenté.
 * Affichage basé sur la consigne de laboratoire.
 *
 * @author Joris Décoppet
 * @author Hugo Ducommun
 * @author Alexis Martins
 * Date : 18.11.2022
 */
public class App {
    public static void main(String[] args) {
        // Professeurs
        Professeur pdo = new Professeur("Pier", "Donini", "PDO");
        Professeur dre = new Professeur("Daniel", "Rossier", "DRE");

        // Leçons
        final Lecon poo1 = new Lecon(pdo, "POO", 3, 6, 2, "H02");
        final Lecon poo2 = new Lecon(pdo, "POO", 4, 6, 2, "H02");
        final Lecon poo3 = new Lecon(pdo, "POO", 4, 8, 2, "H02");
        final Lecon sye1 = new Lecon(dre, "SYE", 1, 1, 2, "G01");
        final Lecon sye2 = new Lecon(dre, "SYE", 4, 3, 2, "A09");
        final Lecon tic = new Lecon("TIC", 2, 10, 1, "F06");

        // Etudiants
        final Etudiant jl = new Etudiant("John", "Lennon", 1234);
        final Etudiant pmc = new Etudiant("Paul", "Mc Cartney", 2341);
        final Etudiant rs = new Etudiant("Ringo", "Starr", 3241);
        final Etudiant gh = new Etudiant("George", "Harisson", 4321);
        final Etudiant rw = new Etudiant("Roger", "Waters", 1324);
        final Etudiant dg = new Etudiant("David", "Gilmour", 4312);

        // Groupes
        final Groupe il6 = new Groupe(6, "IL", 1, new Etudiant[] {jl, pmc, rs, gh});
        final Groupe si6 = new Groupe(6, "SI", 1, new Etudiant[] {rw, dg});

        // Affecte les leçons aux groupes
        il6.definirLecons(poo1, poo2, poo3, sye1, sye2, tic);
        si6.definirLecons(poo1, poo2, poo3);

        // Affichage de la liste des membres du système
        Personne[] membres = new Personne[] {pdo, dre, jl, pmc, rs, gh, rw, dg};
        System.out.println("-- Membres de l'ecole\n");
        for (Personne p : membres) {
            System.out.println(p);
        }
        System.out.println();

        // Affichage des horaires d'un groupe et d'un professeur
        System.out.println(il6.horaire());
        System.out.println(pdo.horaire());
    }
}
