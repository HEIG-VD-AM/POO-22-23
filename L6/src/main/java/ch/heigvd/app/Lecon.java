package ch.heigvd.app;

import java.util.ArrayList;

/**
 * Classe qui représente une leçon de matière dirigée ou non par un professeur.
 * Chaque leçon se déroule un jour de la semaine avec une période de début et
 * une durée dans une salle.
 *
 * @author Joris Décoppet
 * @author Hugo Ducommun
 * @author Alexis Martins
 * Date : 18.11.2022
 */
public class Lecon {
    private final String matiere;
    private final int jourSemaine;
    private final int periodeDebut;
    private final int duree;
    private final String salle;
    private final Professeur professeur;

    /**
     * Constructeur de la classe leçon avec le paramètre Professeur
     * @param professeur    Professeur référent de la leçon
     * @param matiere       Abréviation du cours (ex : SYE)
     * @param jourSemaine   De lundi (1) à vendredi (5)
     * @param periodeDebut  De 1 à 10
     * @param duree         Durée en périodes de la leçon
     * @param salle         Salle sous format "[A-Z][0-9][0-9]"
     */
    public Lecon(Professeur professeur, String matiere, int jourSemaine,
                 int periodeDebut, int duree, String salle) {
        this.professeur = professeur;
        this.matiere = matiere;
        this.jourSemaine = jourSemaine;
        this.periodeDebut = periodeDebut;
        this.duree = duree;
        this.salle = salle;
        if(professeur != null){
            this.professeur.definirLecon(this);
        }
    }

    /**
     * Constructeur de la classe leçon sans le paramètre Professeur
     * @param matiere       Abréviation du cours (ex: SYE)
     * @param jourSemaine   De lundi (1) à vendredi (5)
     * @param periodeDebut  De 1 à 10
     * @param duree         Durée en périodes de la leçon
     * @param salle         Salle sous format "[A-Z][0-9][0-9]"
     */
    public Lecon(String matiere, int jourSemaine, int periodeDebut, int duree,
                 String salle)
    { this(null, matiere, jourSemaine, periodeDebut, duree, salle); }

    /**
     * Affichage d'un horaire au format d'une grille contenant les leçons
     * @param args Liste des leçons à afficher dans l'horaire
     * @return Horaire au format grille
     */
    public static String horaire(ArrayList<Lecon> args) {

        final StringBuilder horaire = new StringBuilder();

        // Déclaration des caractères utilisés pour réaliser la grille
        final String separationCaseHorizontal = "|";
        final String separationCaseVertical = "-";

        final String[] joursSemaine = new String[] {"Lun", "Mar", "Mer",
                                                    "Jeu", "Ven"};
        final String[] heuresPeriodes = new String[] {"8:30", "9:15", "10:25",
                                                      "11:15", "12:00", "13:15",
                                                      "14:00", "14:55", "15:45",
                                                      "16:35", "17:20"};

        // Tableau contenant le texte de la leçon
        final String[][] tableauHoraire =
              new String[heuresPeriodes.length][joursSemaine.length];

        // Tableau indiquant si la case suivante dans le tableau est dans la même leçon
        final boolean[][] prochaineCaseDansLecon =
              new boolean[heuresPeriodes.length][joursSemaine.length];

        // Dimensions du tableau
        final int largeurCase = 13;
        final int espacesDebut = 5;

        // Création d'un tableau à deux dimensions stockant le texte à afficher pour chaque période
        for (Lecon lecon : args) {
            tableauHoraire[lecon.periodeDebut - 1][lecon.jourSemaine - 1] =
            String.format("%-6s%-4s%3s", lecon.matiere, lecon.salle,
            lecon.professeur != null ? lecon.professeur.abreviation() : "");

            for (int i = 0; i < lecon.duree - 1; ++i) {
                prochaineCaseDansLecon[lecon.periodeDebut - 1 + i][lecon.jourSemaine - 1] = true;
            }
        }

        horaire.append(String.format("%" + espacesDebut + "s", ""));

        // Création de l'en-tête du tableau (Jours de la semaine)
        for(String jour : joursSemaine) {
            horaire.append(separationCaseHorizontal + " ");
            horaire.append(String.format("%-" + (largeurCase-1) + "s", jour));
        }
        horaire.append("|\n");

        // Ligne de séparation entre en-tête et horaire
        horaire.append(String.format("%" + espacesDebut + "s", ""));
        for(int i = 0; i < joursSemaine.length; ++i) {
            horaire.append(separationCaseHorizontal);
            horaire.append(separationCaseVertical.repeat(largeurCase));
        }
        horaire.append("|\n");

        for (int i = 0; i < heuresPeriodes.length; ++i) {

            horaire.append(String.format("%" + espacesDebut + "s", heuresPeriodes[i]));

            // Ligne avec l'horaire
            for (int j = 0; j < joursSemaine.length; ++j) {

                horaire.append(separationCaseHorizontal);
                horaire.append(tableauHoraire[i][j] != null ? tableauHoraire[i][j] :
                                        String.format("%" + largeurCase + "s", ""));
            }

            horaire.append(separationCaseHorizontal + "\n");

            // Ligne de séparation entre les périodes
            horaire.append(String.format("%" + espacesDebut + "s", ""));
            for(int j = 0; j < joursSemaine.length; ++j) {
                horaire.append(separationCaseHorizontal);
                if(!prochaineCaseDansLecon[i][j]) {
                    horaire.append(separationCaseVertical.repeat(largeurCase));
                }
                else {
                    horaire.append(String.format("%" + largeurCase + "s", ""));
                }
            }
            horaire.append("|\n");
        }

        return horaire.toString();
    }
}
