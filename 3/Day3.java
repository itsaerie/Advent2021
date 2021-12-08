import java.io.*;
import java.util.*;

public class Day3 {
    public static int part1(ArrayList<String> lines) {
        int[] zeroes = new int[lines.get(0).length()];

        for(String line : lines) {
            for(int i=0; i<line.length(); i++) {
                if(line.charAt(i) == '0') {
                    zeroes[i]++;
                }
            }
        }

        String gamma = "";
        String epsilon = "";

        for(int z : zeroes) {
            if(z > lines.size()/2) {
                gamma += "1";
                epsilon += "0";
            } else {
                gamma += "0";
                epsilon += "1";
            }
        }

        System.out.println(gamma + ":" + Integer.parseInt(gamma, 2));
        System.out.println(epsilon+":" + Integer.parseInt(epsilon,2));

        return Integer.parseInt(gamma,2)*Integer.parseInt(epsilon,2);
    }

    public static long part2(ArrayList<String> lines) {
        // oxygen (common)
        ArrayList<String> oLines = new ArrayList<>(lines);
        for(int i=0; i<oLines.get(0).length(); i++) {
            if(oLines.size() == 1) {
                break;
            }
            // twice twice time
            int z = 0;

            for(String line:oLines) {
                if(line.charAt(i)=='0') {
                    z++;
                }
            }

            if(z*2 <= oLines.size()) { // less than half
                // remove all 0s
                for(int j=0; j<oLines.size(); j++) {
                    if(oLines.get(j).charAt(i)=='0') {
                        oLines.remove(j);
                        j--;
                    }
                }
            } else {
                // remove all 1s
                for(int j=0; j<oLines.size(); j++) {
                    if(oLines.get(j).charAt(i)=='1') {
                        oLines.remove(j);
                        j--;
                    }
                }
            }
        }
        String oxygen = oLines.get(0);
        System.out.println(oxygen+":"+Integer.parseInt(oxygen,2));

        // CO2 (less common)
        ArrayList<String> coLines = new ArrayList<>(lines);
        for(int i=0; i<coLines.get(0).length(); i++) {
            if(coLines.size() == 1) {
                break;
            }
            // twice twice time
            int z = 0;
            for(String line:coLines) {
                if(line.charAt(i)=='0') {
                    z++;
                }
            }
            if(z*2 <= coLines.size()) { // less than half
                // remove all 1s
                for(int j=0; j<coLines.size(); j++) {
                    if(coLines.get(j).charAt(i)=='1') {
                        coLines.remove(j);
                        j--;
                    }
                }
            } else {
                // remove all 1s
                for(int j=0; j<coLines.size(); j++) {
                    if(coLines.get(j).charAt(i)=='0') {
                        coLines.remove(j);
                        j--;
                    }
                }
            }
        }
        String co2 = coLines.get(0);
        System.out.println(co2+":"+Integer.parseInt(co2,2));

        return Integer.parseInt(oxygen,2)*Integer.parseInt(co2,2);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("3/input.txt");
        Scanner sc = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            lines.add(line);
        }
        sc.close();

        int part1 = part1(lines);
        System.out.println(part1);
        long part2 = part2(lines);
        System.out.println(part2);
    }
}
