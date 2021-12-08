import java.io.*;
import java.util.*;

public class Day8 {
    public static int part1(ArrayList<String> lines) {
        int count = 0;
        for (String line : lines) {
            String[] phrases = line.trim().split("\\|");
            String[] values = phrases[1].trim().split("\\s+");
            for (String value : values) {
                if (value.length() == 2 || value.length() == 3 || value.length() == 4 || value.length() == 7) {
                    count++;
                }
            }
        }
        return count;
    }

    // time for hella helper methods
    public static boolean anagram(String str1, String str2) {
        char[] first = str1.toCharArray();
        char[] second = str2.toCharArray();
        Arrays.sort(first);
        Arrays.sort(second);
        return Arrays.equals(first, second);
    }

    // for turning that string into the keys
    public static String[] keySolve(String keyString) {
        String[] keys = new String[10]; // digits 0-9, indexed
        Arrays.fill(keys, "");

        String[] looseKeys = keyString.trim().split("\\s+");

        HashMap<Character, Integer> charOcc = new HashMap<>();
        for (String k : looseKeys) {
            for (char c : k.toCharArray()) {
                if (charOcc.containsKey(c)) {
                    charOcc.put(c, charOcc.get(c) + 1);
                } else {
                    charOcc.put(c, 1);
                }
            }
        }

        // give us our knowns
        for (String k : looseKeys) {
            if (k.length() == 2) {
                keys[1] = k;
            } else if (k.length() == 4) {
                keys[4] = k;
            } else if (k.length() == 3) {
                keys[7] = k;
            } else if (k.length() == 7) {
                keys[8] = k;
            }
        }

        char top = ' '; // done, x8, difference between 7 and 1
        char toL = ' '; // x5
        char toR = ' '; // done, x8
        char mid = ' '; // done, x7, is in 4
        char boL = ' '; // done, x4
        char boR = ' '; // x9, is in 1
        char bot = ' '; // done, x7

        for (int i = 0; i < 3; i++) {
            if (!keys[1].contains("" + keys[7].charAt(i))) {
                top = keys[7].charAt(i);
                charOcc.remove(top); // remove it from occurrences
                break;
            }
        }

        // find every single place
        for (Iterator<Map.Entry<Character, Integer>> it = charOcc.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Character, Integer> entry = it.next();
            switch (entry.getValue()) {
                case 4:
                    boL = entry.getKey();
                    break;
                case 6:
                    toL = entry.getKey();
                    break;
                case 7:
                    if (keys[4].contains("" + entry.getKey())) {
                        mid = entry.getKey();
                    } else {
                        bot = entry.getKey();
                    }
                    break;
                case 8:
                    toR = entry.getKey();
                    break;
                case 9:
                    boR = entry.getKey();
                    break;
                default:
                    break;
            }
            it.remove();
        }

        // set the leftovers
        for (char ch : keys[8].toCharArray()) {
            if (ch != mid) {
                keys[0] += ch;
            }
            if (ch != toL && ch != boR) {
                keys[2] += ch;
            }
            if (ch != toL && ch != boL) {
                keys[3] += ch;
            }
            if (ch != toR && ch != boL) {
                keys[5] += ch;
            }
            if (ch != toR) {
                keys[6] += ch;
            }
            if (ch != boL) {
                keys[9] += ch;
            }
        }

        String digital = "" + top + toL + toR + mid + boL + boR + bot;
        looseKeys[0] = digital;

        return keys;
    }

    public static long part2(ArrayList<String> lines) {
        long count = 0;
        for (String line : lines) {
            String[] phrases = line.trim().split("\\|");
            String[] keys = keySolve(phrases[0]);
            String[] values = phrases[1].trim().split("\\s+");
            int total = 0;

            for (String value : values) { // runs 4x
                for (int i = 0; i < 10; i++) { // runs 10x
                    if (anagram(keys[i], value)) { // O(n) comparison
                        // append digit
                        total *= 10;
                        total += i;
                        break;
                    }
                }
            }
            count += total;
        }
        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("8/input.txt");
        Scanner sc = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            lines.add(line);
        }
        sc.close();

        int part1 = part1(lines);
        System.out.println(part1);
        long startTime = System.nanoTime();
        long part2 = part2(lines);
        System.out.println(part2);
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000000000.00);
    }
}
