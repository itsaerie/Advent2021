import java.io.*;
import java.util.*;

public class Day4 {
    public static int part1(String callOrder, ArrayList<BingoBoard> boards) {
        String[] nums = callOrder.split(",");
        for (String num : nums) {
            for (BingoBoard b : boards) {
                b.callNum(Integer.parseInt(num));
                if (b.hasBingo()) {
                    return Integer.parseInt(num) * b.nonNegSum();
                }
            }
        }
        return 0;
    }

    public static long part2(String callOrder, ArrayList<BingoBoard> boards) {
        String[] nums = callOrder.split(",");
        for (String num : nums) {
            for (int i = 0; i < boards.size(); i++) {
                boards.get(i).callNum(Integer.parseInt(num));
                if (boards.get(i).hasBingo()) {
                    if (boards.size() == 1) {
                        return Integer.parseInt(num) * boards.get(i).nonNegSum();
                    } else {
                        boards.remove(i);
                        i--;
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("4/input.txt");
        Scanner sc = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            lines.add(line);
        }
        sc.close();

        // input to numbers and bingo board
        String callNum = lines.get(0);
        lines.remove(0);

        ArrayList<BingoBoard> boards = new ArrayList<>();
        while (lines.size() > 0) {
            lines.remove(0);
            BingoBoard b = new BingoBoard(lines.get(0), lines.get(1), lines.get(2), lines.get(3), lines.get(4));
            lines.remove(0);
            lines.remove(0);
            lines.remove(0);
            lines.remove(0);
            lines.remove(0);
            boards.add(b);
        }

        int part1 = part1(callNum, boards);
        System.out.println(part1);
        long part2 = part2(callNum, boards);
        System.out.println(part2);
    }
}
