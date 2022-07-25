package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int m = 100;
        int O = 1;
        int n = 80;
        ArrayList<Integer> list = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < m; i++) {
            list.add(random.nextInt(O, n));
        }
        playGame(list.toArray(new Integer[list.size()]), O);

    }

    public static String playGame(Integer[] array, int O) {
        if (array.length == 0) {
            return "bob";
        }
        for (Integer integer : array) {
            if (integer == O) {
                return "Alice";
            }
        }
        ArrayList<Integer> num1 = new ArrayList<>();
        ArrayList<Integer> num2 = new ArrayList<>();

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length / 2; i++) {
            Random random = new Random();
            int ran = random.nextInt(0, array.length);
            if (set.contains(ran)) {
                continue;
            }
            set.add(ran);
        }

        for (int i = 0; i < array.length; i++) {
            if (set.contains(i)) {
                num1.add(array[i]);
            } else {
                num2.add(array[i]);
            }
        }


        return null;
    }

}
