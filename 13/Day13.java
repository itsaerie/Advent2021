import java.io.*;
import java.util.*;
import java.awt.Point;

public class Day13 {
    public static HashSet<Point> fold(HashSet<Point> points, String fold) {
        HashSet<Point> newPoints = new HashSet<>();
        String[] parsed = fold.split("=");
        int line = Integer.parseInt(parsed[1]);
        if (parsed[0].equals("x")) {
            // horizontal fold
            // all values with x right of line are folded
            for (Point p : points) {
                if (p.x > line) {
                    // System.out.println("folding "+p+" on "+fold+" to "+new Point(line - (p.x - line), p.y));
                    newPoints.add(new Point(line - (p.x - line), p.y));
                } else if(p.x < line){
                    // System.out.println("keeping "+p);
                    newPoints.add(new Point(p.x, p.y));
                }
            }
        } else {
            // vertical fold
            // all values with y above line are folded
            for (Point p : points) {
                if (p.y > line) {
                    // System.out.println("folding "+p+" on "+fold+" to "+new Point(p.x, line - (p.y - line)));
                    newPoints.add(new Point(p.x, line - (p.y - line)));
                } else if(p.y < line) {
                    // System.out.println("keeping "+p);
                    newPoints.add(new Point(p.x, p.y));
                }
            }
        }
        
        // System.out.println("new size: "+newPoints.size());

        return newPoints;
    }

    public static void printGrid(HashSet<Point> points) {
        char[][] grid = new char[6][40];
        for(char[] line : grid) {
            Arrays.fill(line,' ');
        }
        for(Point p : points) {
            grid[p.y][p.x] = '#';
        }
        for(char[] line : grid) {
            System.out.println(Arrays.toString(line));
        }
    }

    public static int part1(HashSet<Point> points, ArrayList<String> folds) {
        // parse first fold
        HashSet<Point> newPoints = fold(points, folds.get(0));

        return newPoints.size();
    }

    public static void part2(HashSet<Point> points, ArrayList<String> folds) {
        HashSet<Point> newPoints = new HashSet<>();
        newPoints.addAll(points);
        for(int i=0; i<folds.size(); i++) {
            newPoints = fold(newPoints, folds.get(i));
        }
        printGrid(newPoints);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("13/input.txt");
        Scanner sc = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            lines.add(line);
        }
        sc.close();

        // split line into Points and Folds
        HashSet<Point> points = new HashSet<>();
        ArrayList<String> folds = new ArrayList<>();

        for (String line : lines) {
            if (line.contains(",")) {
                String[] split = line.trim().split(",");
                points.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
            } else if (line.contains("=")) {
                String[] split = line.trim().split("\\s+");
                folds.add(split[2]);
            }
        }

        // System.out.println(""+points.size()+" points");
        // System.out.println(""+fold.size()+" folds");

        long startTime, endTime;
        startTime = System.nanoTime();
        int part1 = part1(points, folds);
        System.out.println(part1);
        endTime = System.nanoTime();
        System.out.println("Pt1 "+(endTime - startTime) / 1000000000.00+" seconds");
        startTime = System.nanoTime();
        part2(points, folds);
        endTime = System.nanoTime();
        System.out.println("Pt2 "+(endTime - startTime) / 1000000000.00+" seconds");
    }
}
