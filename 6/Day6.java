import java.io.*;
import java.util.*;

public class Day6 {
    public static int part1(int[] nums) {
        int days = 80;

        // lanternfish are limited to a certain cycle
        int[] lanternTimer = new int[9];

        for(int n : nums) {
            lanternTimer[n]++;
        }

        // debug
        // System.out.println("init  "+Arrays.toString(lanternTimer)+" "+Arrays.stream(lanternTimer).sum());

        for(int i=1; i<=days; i++) {
            // step 1 is to hold the most recent num: the number of new fish to spawn and timers to reset
            int spawn = lanternTimer[0];
            // then push all the timers down
            for(int j=0; j<lanternTimer.length-1; j++) {
                lanternTimer[j] = lanternTimer[j+1];
            }
            lanternTimer[8] = 0;
            // add values
            lanternTimer[6] += spawn; // fish have new timer of 6
            lanternTimer[8] += spawn; // fish have new timer of 8

            // debug
            // System.out.println("day "+i+" "+Arrays.toString(lanternTimer)+" "+Arrays.stream(lanternTimer).sum());
        }

        int count = 0;
        for(int l : lanternTimer) {
            count += l;
        }

        return count;
    }

    public static long part2(int[] nums) {
        int days = 256;

        // lanternfish are limited to a certain cycle
        long[] lanternTimer = new long[9];
        for(int n : nums) {
            lanternTimer[n]++;
        }

        for(int i=1; i<=days; i++) {
            // step 1 is to hold the most recent num: the number of new fish to spawn and timers to reset
            long spawn = lanternTimer[0];
            // then push all the timers down
            for(int j=0; j<lanternTimer.length-1; j++) {
                lanternTimer[j] = lanternTimer[j+1];
            }
            lanternTimer[8] = 0;
            // add values
            lanternTimer[6] += spawn; // fish have new timer of 6
            lanternTimer[8] += spawn; // fish have new timer of 8
        }

        long count = 0;
        for(long l : lanternTimer) {
            count += l;
        }

        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("6/input.txt");
        Scanner sc = new Scanner(f);
        String line = sc.nextLine();
        sc.close();

        String[] spawns = line.trim().split(",");
        int[] nums = new int[spawns.length];
        for(int i=0; i<spawns.length; i++) {
            nums[i] = Integer.parseInt(spawns[i]);
        }

        int part1 = part1(nums);
        System.out.println(part1);
        long part2 = part2(nums);
        System.out.println(part2);
    }
}
