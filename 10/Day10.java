import java.io.*;
import java.util.*;

public class Day10 {
    public static int part1(ArrayList<String> lines) {
        int score = 0;
        for (int i=0; i<lines.size(); i++) {
            // stop at first unbalanced thing
            Deque<Character> seen = new LinkedList<Character>();
            for (int j = 0; j < lines.get(i).length(); j++) {
                char ch = lines.get(i).charAt(j);
                if (isLeft(ch)) {
                    seen.addFirst(ch);
                } else {
                    if (closes(seen.peekFirst(), ch)) {
                        seen.pop();
                    } else {
                        // System.out.println(""+ch+" at "+i);
                        score += giveScore1(ch);
                        // remove line
                        lines.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
        return score;
    }

    public static boolean isLeft(char ch) {
        return ch == '(' || ch == '[' || ch == '{' || ch == '<';
    }

    public static int giveScore1(char ch) {
        if (ch == ')') {
            return 3;
        }
        if (ch == ']') {
            return 57;
        }
        if (ch == '}') {
            return 1197;
        }
        if (ch == '>') {
            return 25137;
        }
        return 0;
    }

    public static int giveScore2(char ch) {
        if (ch == ')') {
            return 1;
        }
        if (ch == ']') {
            return 2;
        }
        if (ch == '}') {
            return 3;
        }
        if (ch == '>') {
            return 4;
        }
        return 0;
    }

    public static boolean closes(char left, char right) {
        if (left == '(' && right == ')') {
            return true;
        }
        if (left == '{' && right == '}') {
            return true;
        }
        if (left == '[' && right == ']') {
            return true;
        }
        if (left == '<' && right == '>') {
            return true;
        }
        return false;
    }

    public static char giveMatch(char left) {
        if (left == '(') {
            return ')';
        }
        if (left == '[') {
            return ']';
        }
        if (left == '{') {
            return '}';
        }
        if (left == '<') {
            return '>';
        }
        return ' ';
    }

    public static long part2(ArrayList<String> lines) {
        ArrayList<Long> scores = new ArrayList<Long>();
        for (String line : lines) {
            long lineScore = 0;
            // stop at first unbalanced thing
            Deque<Character> seen = new LinkedList<Character>();
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);

                if (isLeft(ch)) {
                    seen.addFirst(ch);
                } else {
                    if (closes(seen.peekFirst(), ch)) {
                        seen.pop();
                    } else {
                        // add until it matches
                        while (!seen.isEmpty() && !closes(seen.peek(), ch)) {
                            char chr = giveMatch(seen.pop());
                            lineScore = lineScore * 5 + giveScore2(chr);
                        }
                    }
                }
            }
            // add to end
            while(!seen.isEmpty()) {
                char chr = giveMatch(seen.pop());
                lineScore = (lineScore * 5) + giveScore2(chr);
            }
            scores.add(lineScore);
        }
        long[] sorted = scores.stream().mapToLong(i -> i).toArray();
        Arrays.sort(sorted);
        return sorted[scores.size()/2];
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("10/input.txt");
        Scanner sc = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            lines.add(line);
        }
        sc.close();

        long startTime, endTime;
        startTime = System.nanoTime();
        int part1 = part1(lines);
        System.out.println(part1);
        endTime = System.nanoTime();
        System.out.println(""+(endTime - startTime) / 1000000000.00+" seconds");
        startTime = System.nanoTime();
        long part2 = part2(lines);
        System.out.println(part2);
        endTime = System.nanoTime();
        System.out.println(""+(endTime - startTime) / 1000000000.00+" seconds");
    }
}
