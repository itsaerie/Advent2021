import java.io.*;
import java.util.*;

public class Day2 {
    public static int part1(ArrayList<String> lines) {
        int horiz = 0;
        int depth = 0;

        for (int i = 0; i < lines.size(); i++) {
            String[] splitStr = lines.get(i).trim().split("\\s+");

            switch (splitStr[0]) {
                case "forward":
                    horiz += Integer.parseInt(splitStr[1]);
                    break;
                case "down":
                    depth += Integer.parseInt(splitStr[1]);
                    break;
                case "up":
                    depth -= Integer.parseInt(splitStr[1]);
                    break;
                default:
                    break;
            }
        }

        System.out.println("Horiz:"+horiz);
        System.out.println("Depth:"+depth);
        
        return horiz*depth;
    }

    public static long part2(ArrayList<String> lines) {
        long horiz = 0;
        long depth = 0;
        long aim = 0;

        for (String line : lines) {
            String[] splitStr = line.trim().split("\\s+");

            switch (splitStr[0]) {
                case "forward":
                    horiz += Integer.parseInt(splitStr[1]);
                    depth += aim * Integer.parseInt(splitStr[1]);
                    break;
                case "down":
                    aim += Integer.parseInt(splitStr[1]);
                    break;
                case "up":
                    aim -= Integer.parseInt(splitStr[1]);
                    break;
                default:
                    break;
            }
        }

        System.out.println("Horiz:"+horiz);
        System.out.println("Depth:"+depth);

        return horiz*depth;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("2/input.txt");
        Scanner sc = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            lines.add(line);
        }
        sc.close();

        int part1 = part1(lines);
        System.out.println("Part 1: "+part1);
        long part2 = part2(lines);
        System.out.println("Part 2: "+part2);
    }
}
