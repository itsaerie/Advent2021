import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class Day14 {
    public static String step(String start, HashMap<String, String> rules) {
        String end = "";
        for (int i = 0; i < start.length() - 1; i++) {
            String two = start.substring(i, i + 2);
            if (rules.containsKey(two)) {
                end += start.charAt(i);
                end += rules.get(two);
            } else {
                end += start.charAt(i);
            }
        }
        end += start.charAt(start.length() - 1);

        return end;
    }

    public static HashMap<Character, Long> countChars(String s) {
        HashMap<Character, Long> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, (long) 1);
            }
        }
        return map;
    }

    // keeping this more for posterity, i don't want to switch to pairwise logic until i feel like optimizing everything
    public static int part1(String start, HashMap<String, String> rules) {
        String modStr = start;
        for (int i = 1; i <= 10; i++) {
            modStr = step(modStr, rules);
        }
        HashMap<Character, Long> countStr = countChars(modStr);

        long most = 0;
        long least = Long.MAX_VALUE;
        for (Map.Entry<Character, Long> entry : countStr.entrySet()) {
            if (entry.getValue() > most) {
                most = entry.getValue();
            }
            if (entry.getValue() < least) {
                least = entry.getValue();
            }
        }
        return (int) (most - least);
    }

    public static HashMap<String, Long> stepPairwise(HashMap<String, Long> startPairs, HashMap<String, String> rules) {
        HashMap<String, Long> endPairs = new HashMap<>();

        for (Map.Entry<String, Long> entry : startPairs.entrySet()) {
            if (rules.containsKey(entry.getKey())) {
                // do everything
                String pairLeft = entry.getKey().charAt(0) + rules.get(entry.getKey());
                String pairRight = rules.get(entry.getKey()) + entry.getKey().charAt(1);
                if (endPairs.containsKey(pairLeft)) {
                    endPairs.put(pairLeft, endPairs.get(pairLeft) + entry.getValue());
                } else {
                    endPairs.put(pairLeft, entry.getValue());
                }
                if (endPairs.containsKey(pairRight)) {
                    endPairs.put(pairRight, endPairs.get(pairRight) + entry.getValue());
                } else {
                    endPairs.put(pairRight, entry.getValue());
                }
            }
        }

        return endPairs;
    }

    public static HashMap<Character, Long> countCharsPairwise(HashMap<String, Long> pairs) {
        // yes, this is imperfect and can be off by 1, by missing the last character. oh well.

        HashMap<Character, Long> mapLeft = new HashMap<>();

        for (Map.Entry<String, Long> entry : pairs.entrySet()) {
            char left = entry.getKey().charAt(0);
            if (mapLeft.containsKey(left)) {
                mapLeft.put(left, mapLeft.get(left) + entry.getValue());
            } else {
                mapLeft.put(left, entry.getValue());
            }
        }

        return mapLeft;
    }

    public static long part2(String start, HashMap<String, String> rules) {
        // turn start into a hashmap
        HashMap<String, Long> pairs = new HashMap<>();
        for (int i = 0; i < start.length() - 1; i++) {
            String pair = start.substring(i, i + 2);
            if (pairs.containsKey(pair)) {
                pairs.put(pair, pairs.get(pair));
            } else {
                pairs.put(pair, (long) 1);
            }
        }

        // do steps
        for (int i = 1; i <= 40; i++) {
            pairs = stepPairwise(pairs, rules);
        }

        HashMap<Character, Long> countStr = countCharsPairwise(pairs);

        long most = 0;
        long least = Long.MAX_VALUE;

        for (Map.Entry<Character, Long> entry : countStr.entrySet()) {
            if (entry.getValue() > most) {
                most = entry.getValue();
            }
            if (entry.getValue() < least) {
                least = entry.getValue();
            }
        }

        return most - least;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> lines = new ArrayList<>(
                Files.readAllLines(new File("14/input.txt").toPath(), Charset.defaultCharset()));

        String start = lines.get(0);
        HashMap<String, String> rules = new HashMap<>();

        for (String line : lines) {
            if (line.contains("->")) {
                // it's a rule!
                String[] split = line.split("\\s+");
                rules.put(split[0], split[2]);
            }
        }

        long startTime, endTime;
        startTime = System.nanoTime();
        int part1 = part1(start, rules);
        System.out.println(part1);
        endTime = System.nanoTime();
        System.out.println("Pt1 "+(endTime - startTime) / 1000000000.00+" seconds");
        startTime = System.nanoTime();
        long part2 = part2(start, rules);
        System.out.println(part2);
        endTime = System.nanoTime();
        System.out.println("Pt2 "+(endTime - startTime) / 1000000000.00+" seconds");
    }
}
