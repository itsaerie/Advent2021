import java.io.*;
import java.util.*;
import java.awt.Point;

public class Day11 {
    public static int part1(int[][] grid) {
        int steps = 100;
        int flashes = 0;

        for (int step = 1; step <= steps; step++) {
            // System.out.println("Step " + step);

            HashSet<Point> flashed = new HashSet<>();
            Deque<Point> toCheck = new LinkedList<Point>();

            // increment first
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    grid[i][j]++;
                    if (grid[i][j] > 9) {
                        toCheck.add(new Point(i, j));
                    }
                }
            }

            // count flashes
            while (!toCheck.isEmpty()) {
                Point p = toCheck.pop();
                // is has flashed this step
                if (flashed.contains(p)) {
                    continue;
                }
                // flash then set to 0
                if (grid[p.x][p.y] > 9) {
                    // System.out.println(""+p+" flashes "+grid[p.x][p.y]);
                    flashed.add(p);
                    flashes++;
                    grid[p.x][p.y] = 0;

                    for (Point pAdj : adjacentPoints(p)) {
                        if (pAdj.x >= 0 && pAdj.x < grid.length && pAdj.y >= 0 && pAdj.y < grid.length) {
                            grid[pAdj.x][pAdj.y]++;
                            toCheck.add(pAdj);
                        }
                    }
                }
            }

            // set all flashed ones back to 0
            for (Point p : flashed) {
                grid[p.x][p.y] = 0;
            }
        }
        return flashes;
    }

    public static Point[] adjacentPoints(Point p) {
        Point[] adja = new Point[8];
        adja[0] = new Point(p.x - 1, p.y - 1);
        adja[1] = new Point(p.x - 1, p.y);
        adja[2] = new Point(p.x - 1, p.y + 1);
        adja[3] = new Point(p.x, p.y - 1);
        adja[4] = new Point(p.x, p.y + 1);
        adja[5] = new Point(p.x + 1, p.y - 1);
        adja[6] = new Point(p.x + 1, p.y);
        adja[7] = new Point(p.x + 1, p.y + 1);
        return adja;
    }

    public static long part2(int[][] grid) {
        int step = 1;
        boolean allFlashed = false;

        while (!allFlashed) {
            HashSet<Point> flashed = new HashSet<>();
            Deque<Point> toCheck = new LinkedList<Point>();

            // increment first
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    grid[i][j]++;
                    if (grid[i][j] > 9) {
                        toCheck.add(new Point(i, j));
                    }
                }
            }

            // count flashes
            while (!toCheck.isEmpty()) {
                Point p = toCheck.pop();
                // is has flashed this step
                if (flashed.contains(p)) {
                    continue;
                }
                // flash then set to 0
                if (grid[p.x][p.y] > 9) {
                    // System.out.println(""+p+" flashes "+grid[p.x][p.y]);
                    flashed.add(p);
                    grid[p.x][p.y] = 0;

                    for (Point pAdj : adjacentPoints(p)) {
                        if (pAdj.x >= 0 && pAdj.x < grid.length && pAdj.y >= 0 && pAdj.y < grid.length) {
                            grid[pAdj.x][pAdj.y]++;
                            toCheck.add(pAdj);
                        }
                    }
                }
            }

            // set all flashed ones back to 0
            for (Point p : flashed) {
                grid[p.x][p.y] = 0;
            }

            boolean checkFlash = true;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    if (grid[i][j] != 0) {
                        checkFlash = false;
                        continue;
                    }
                }
                if (grid[i][0] != 0) {
                    checkFlash = false;
                    continue;
                }
            }
            allFlashed = checkFlash;
            if(checkFlash) {
                break;
            }

            step++;
        }
        return step;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("11/input.txt");
        Scanner sc = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            lines.add(line);
        }
        sc.close();

        // turn into grid
        int[][] grid = new int[10][10];
        int[][] grid2= new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = Integer.parseInt("" + lines.get(i).charAt(j));
                grid2[i][j] = Integer.parseInt("" + lines.get(i).charAt(j));
            }
        }

        long startTime, endTime;
        startTime = System.nanoTime();
        int part1 = part1(grid);
        System.out.println(part1);
        endTime = System.nanoTime();
        System.out.println(""+(endTime - startTime) / 1000000000.00+" seconds");
        startTime = System.nanoTime();
        long part2 = part2(grid2);
        System.out.println(part2);
        endTime = System.nanoTime();
        System.out.println(""+(endTime - startTime) / 1000000000.00+" seconds");
    }
}
