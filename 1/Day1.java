import java.io.*;
import java.util.*;

public class Day1 {
    public static int part1(ArrayList<String> lines) {
        int count = 0;

        for(int i=1; i<lines.size(); i++) {
            if(Long.parseLong(lines.get(i))>Long.parseLong(lines.get(i-1))) {
                count++;
            }
        }

        return count;
    }

    public static int part2(ArrayList<String> lines) {
        int count = 0;

        for(int i=1; i<lines.size()-2; i++) {
            long s1 = Long.parseLong(lines.get(i-1)) + Long.parseLong(lines.get(i)) + Long.parseLong(lines.get(i+1));
            long s2 = Long.parseLong(lines.get(i)) + Long.parseLong(lines.get(i+1)) + Long.parseLong(lines.get(i+2));
            if(s1 < s2) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        final String dir = System.getProperty("user.dir");
        File f = new File(dir,"1/input.txt");
        System.out.println(f.getAbsolutePath());
        Scanner sc = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            lines.add(line);
        }
        sc.close();

        int ans1 = part1(lines);
        System.out.println("1:"+ans1);

        int ans2 = part2(lines);
        System.out.println("2:"+ans2);
    }
}
