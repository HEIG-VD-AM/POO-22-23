package Lab4;

import Lab4.Int;
import Lab4.TriTableauEntiers;
import Lab4.TriTableauObjets;

public class Application {
    public static void main(String[] args) {
        Integer[] tabInt = TriTableauEntiers.stringArrayToIntegerArray(args);
        for (Integer integer : tabInt)
            System.out.println(integer + " ");
    }
}
