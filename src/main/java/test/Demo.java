package test;

import java.nio.charset.StandardCharsets;

public class Demo {
    public static boolean Square(int x) {
        byte[] nums = String.valueOf(x).getBytes(StandardCharsets.UTF_8);

        int count = 0;
        while (count < nums.length) {
            if (nums[count] != nums[nums.length - count-1]) return false;
            count++;
        }
        return true;
    }

    //    46340
    public static void main(String[] args) {
        int x = 12321;
        System.out.println(Square(x));
    }
}
