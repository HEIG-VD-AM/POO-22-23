package Lab4;

import java.util.List;
import java.util.Collections;

public class TriTableauEntiers {
    static int stringToInt(String str) {
        int i = 0, number = 0;
        boolean isNegative = false;
        int len = str.length();
        if (str.charAt(0) == '-') {
            isNegative = true;
            i = 1;
        }
        while (i < len) {
            number *= 10;
            number += (str.charAt(i++) - '0');
        }
        if (isNegative)
            number = -number;
        return number;
    }

    static Integer[] stringArrayToIntegerArray(String[] args) {
        Integer[] tabInt = new Integer[args.length];
        bubbleSort(tabInt);
        for (int i = 0; i < args.length; ++i) {
            tabInt[i] = stringToInt(args[i]);
        }

        return tabInt;
    }

    static void bubbleSort(Integer[] arr) {
        int n = arr.length;
        Integer temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] > arr[j]) {
                    //swap elements
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
