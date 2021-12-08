import java.awt.Point;
import java.io.*;
import java.util.*;

public class Day5 {
    public static int part1(HashMap<Point, ArrayList<Point>> vents) {
        HashMap<Point, Integer> countFreq = new HashMap<>();

        // adding all vert/horiz lines
        for (Map.Entry<Point, ArrayList<Point>> entry : vents.entrySet()) {
            Point v = entry.getKey();
            ArrayList<Point> l1 = entry.getValue();
            for (Point vEnd : l1) {
                // check x-x, y-y
                if (v.x == vEnd.x) {
                    // is line
                    if (v.y <= vEnd.y) {
                        for (int i = v.y; i <= vEnd.y; i++) {
                            Point p = new Point(v.x, i);
                            if (countFreq.containsKey(p)) {
                                countFreq.put(p, countFreq.get(p) + 1);
                            } else {
                                countFreq.put(p, 1);
                            }
                        }
                    } else {
                        for (int i = vEnd.y; i <= v.y; i++) {
                            Point p = new Point(v.x, i);
                            if (countFreq.containsKey(p)) {
                                countFreq.put(p, countFreq.get(p) + 1);
                            } else {
                                countFreq.put(p, 1);
                            }
                        }
                    }
                } else if (v.y == vEnd.y) {
                    // is line
                    if (v.x <= vEnd.x) {
                        for (int i = v.x; i <= vEnd.x; i++) {
                            Point p = new Point(i, v.y);
                            if (countFreq.containsKey(p)) {
                                countFreq.put(p, countFreq.get(p) + 1);
                            } else {
                                countFreq.put(p, 1);
                            }
                        }
                    } else {
                        for (int i = vEnd.x; i <= v.x; i++) {
                            Point p = new Point(i, v.y);
                            if (countFreq.containsKey(p)) {
                                countFreq.put(p, countFreq.get(p) + 1);
                            } else {
                                countFreq.put(p, 1);
                            }
                        }
                    }
                }
            }
        }

        int count = 0;
        // count freqs
        for (Map.Entry<Point, Integer> entry : countFreq.entrySet()) {
            if (entry.getValue() > 1) {
                count++;
            }
        }

        return count;
    }

    public static long part2(HashMap<Point, ArrayList<Point>> vents) {
        HashMap<Point, Integer> countFreq = new HashMap<>();

        // adding all vert/horiz lines
        for (Map.Entry<Point, ArrayList<Point>> entry : vents.entrySet()) {
            Point v = entry.getKey();
            ArrayList<Point> l1 = entry.getValue();
            for (Point vEnd : l1) {
                // System.out.println("Running " + v + "->" + vEnd);

                // check x-x, y-y
                if (v.x == vEnd.x) {
                    // is line
                    if (v.y <= vEnd.y) {
                        for (int i = v.y; i <= vEnd.y; i++) {
                            Point p = new Point(v.x, i);
                            if (countFreq.containsKey(p)) {
                                countFreq.put(p, countFreq.get(p) + 1);
                            } else {
                                countFreq.put(p, 1);
                            }
                        }
                    } else {
                        for (int i = vEnd.y; i <= v.y; i++) {
                            Point p = new Point(v.x, i);
                            if (countFreq.containsKey(p)) {
                                countFreq.put(p, countFreq.get(p) + 1);
                            } else {
                                countFreq.put(p, 1);
                            }
                        }
                    }
                } else if (v.y == vEnd.y) {
                    // is line
                    if (v.x <= vEnd.x) {
                        for (int i = v.x; i <= vEnd.x; i++) {
                            Point p = new Point(i, v.y);
                            if (countFreq.containsKey(p)) {
                                countFreq.put(p, countFreq.get(p) + 1);
                            } else {
                                countFreq.put(p, 1);
                            }
                        }
                    } else {
                        for (int i = vEnd.x; i <= v.x; i++) {
                            Point p = new Point(i, v.y);
                            if (countFreq.containsKey(p)) {
                                countFreq.put(p, countFreq.get(p) + 1);
                            } else {
                                countFreq.put(p, 1);
                            }
                        }
                    }
                } else if (v.x < vEnd.x && v.y < vEnd.y) { // UL -> DR
                    for (int i = 0; i <= vEnd.x - v.x; i++) {
                        Point p = new Point(v.x + i, v.y + i);
                        if (countFreq.containsKey(p)) {
                            countFreq.put(p, countFreq.get(p) + 1);
                        } else {
                            countFreq.put(p, 1);
                        }
                    }
                } else if (vEnd.x < v.x && vEnd.y < v.y) { // DR -> UL
                    for (int i = 0; i <= v.x - vEnd.x; i++) {
                        Point p = new Point(vEnd.x + i, vEnd.y + i);
                        if (countFreq.containsKey(p)) {
                            countFreq.put(p, countFreq.get(p) + 1);
                        } else {
                            countFreq.put(p, 1);
                        }
                    }
                } else if (v.x < vEnd.x) { // downleft diag
                    for (int i = 0; i <= vEnd.x - v.x; i++) {
                        Point p = new Point(v.x + i, v.y - i);
                        if (countFreq.containsKey(p)) {
                            countFreq.put(p, countFreq.get(p) + 1);
                        } else {
                            countFreq.put(p, 1);
                        }
                    }
                } else if (v.x > vEnd.x) {
                    for (int i = 0; i <= v.x - vEnd.x; i++) {
                        Point p = new Point(v.x - i, v.y + i);
                        if (countFreq.containsKey(p)) {
                            countFreq.put(p, countFreq.get(p) + 1);
                        } else {
                            countFreq.put(p, 1);
                        }
                    }
                }

                // System.out.println("Size: " + countFreq.size());
            }
        }

        int count = 0;
        // count freqs
        for (Map.Entry<Point, Integer> entry : countFreq.entrySet()) {
            if (entry.getValue() > 1) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("5/input.txt");
        Scanner sc = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            lines.add(line);
        }
        sc.close();

        // maps of points..?
        HashMap<Point, ArrayList<Point>> vents = new HashMap<>();

        // make pairs of points basically?
        for (String line : lines) {
            // parse line into 3
            String[] parsedLine = line.trim().split("\\s+");
            String[] pStr1 = parsedLine[0].trim().split(",");
            String[] pStr2 = parsedLine[2].trim().split(",");

            Point p1 = new Point(Integer.parseInt(pStr1[0]), Integer.parseInt(pStr1[1]));
            Point p2 = new Point(Integer.parseInt(pStr2[0]), Integer.parseInt(pStr2[1]));

            if (vents.containsKey(p1)) {
                vents.get(p1).add(p2);
            } else {
                ArrayList<Point> l1 = new ArrayList<>();
                l1.add(p2);
                vents.put(p1, l1);
            }
        }

        int part1 = part1(vents);
        System.out.println(part1);
        long part2 = part2(vents);
        System.out.println(part2);
    }
}
