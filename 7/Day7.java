import java.io.*;
import java.util.*;

public class Day7 {
    public static int part1(int[] nums) {
        // presort
        Arrays.sort(nums);

        int median = nums[nums.length / 2];

        int sum = 0;
        for (int i : nums) {
            sum += Math.abs(i - median);
        }

        return sum;
    }

    public static long part2(int[] nums) {
        long total = 0;
        for (int i : nums) {
            total += i;
        }
        long average = Math.round(total / (1.0 * nums.length));

        long[] check = new long[3];
        for(int i=0; i<3; i++) {
            long sum = 0;
            for (int j : nums) {
                long compoundSum = compoundSum(Math.abs(average + i - 1 - j));
                sum += compoundSum;
            }
            check[i] = sum;
        }
        System.out.println(Arrays.toString(check));

        long sum = 0;
        for (int i : nums) {
            long compoundSum = compoundSum(Math.abs(average - i));
            sum += compoundSum;
        }

        return sum;
    }

    public static long compoundSum(long i) {
        long value = 0;
        if (i % 2 == 0) {
            value = (i + 1) * (i / 2);
        } else {
            value = (i * (i - 1) / 2) + i;
        }
        return value;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("7/input.txt");
        Scanner sc = new Scanner(f);
        String line = sc.nextLine();
        sc.close();

        String[] crabs = line.trim().split(",");
        int[] nums = new int[crabs.length];
        for (int i = 0; i < crabs.length; i++) {
            nums[i] = Integer.parseInt(crabs[i]);
        }

        int part1 = part1(nums);
        System.out.println(part1);
        long part2 = part2(nums);
        System.out.println(part2);
    }
}
