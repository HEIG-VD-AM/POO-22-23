public class NouvelleStar {

    public static void main(String[] args)
    {
        final int VOTES_NUMBER = 150;
        int[] voteCounters = new int[args.length];

        if (args.length == 0)
            System.out.println("Il n\'existe pas de candidats !");
        else {
            System.out.println("Candidats:");

            for (int i = 0; i < args.length; ++i) {
                System.out.println("#" + (i + 1) + " " + args[i]);
            }

            System.out.println("\n" + VOTES_NUMBER + " votes:");

            for (int i = 0; i < VOTES_NUMBER; ++i) {
                System.out.print("*");

                java.util.Random random = new java.util.Random();
                int value = random.nextInt(args.length);

                ++voteCounters[value];
            }

            System.out.println("\n\nRésultats:");

            for (int i = 0; i < args.length; ++i) {
                System.out.println(Math.round(((double) voteCounters[i])/VOTES_NUMBER * 100 )  + "% " + args[i]);
            }
        }
    }
}
