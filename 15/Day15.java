import java.awt.*;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class Day15 {
    public static long nonZeroMin(long a, long b, long c, long d) {
        long min = Math.max(Math.max(a, b), Math.max(c, d));
        if (a != 0) {
            min = Math.min(min, a);
        }
        if (b != 0) {
            min = Math.min(min, b);
        }
        if (c != 0) {
            min = Math.min(min, c);
        }
        if (d != 0) {
            min = Math.min(min, d);
        }

        return min;
    }

    public static long leastDist(int[][] grid, HashMap<Point, Long> values, Point p) {
        long min = Long.MAX_VALUE;
        for (Point pAdj : adjacentPoints(grid, p)) {
            if (values.containsKey(pAdj) && values.get(pAdj) < min) {
                min = values.get(pAdj);
            }
        }
        return grid[p.x][p.y] + min;
    }

    public static ArrayList<Point> adjacentPoints(int[][] grid, Point p) {
        ArrayList<Point> li = new ArrayList<>();
        if (p.x != 0) {
            li.add(new Point(p.x - 1, p.y));
        }
        if (p.y != 0) {
            li.add(new Point(p.x, p.y - 1));
        }
        if (p.x != grid.length - 1) {
            li.add(new Point(p.x + 1, p.y));
        }
        if (p.y != grid[0].length - 1) {
            li.add(new Point(p.x, p.y + 1));
        }
        return li;
    }

    public static long part1(int[][] grid) {
        HashMap<Point, Long> values = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(10, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (o1.value <= o2.value ? 1 : 0);
            }
        });

        values.put(new Point(0, 0), (long) 0);
        pq.offer(new Node(new Point(0, 1), 0));
        pq.offer(new Node(new Point(1, 0), 0));
        while (!pq.isEmpty()) {
            Node n = pq.poll();
            if(values.containsKey(n.p)) {
                continue;
            }
            values.put(n.p, leastDist(grid, values, n.p));
            for (Point adjPoint : adjacentPoints(grid, n.p)) {
                if (!values.containsKey(adjPoint)) {
                    pq.offer(new Node(adjPoint,values.get(n.p)+grid[adjPoint.x][adjPoint.y]));
                }
            }
        }

        long[][] newGrid = new long[grid.length][grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                newGrid[i][j] = values.get(new Point(i, j));
            }
        }
        for (long[] row : newGrid) {
            System.out.println(Arrays.toString(row));
        }

        return values.get(new Point(grid.length - 1, grid[0].length - 1));
    }

    public static long part2(int[][] grid) {
        return 0;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> lines = new ArrayList<>(
                Files.readAllLines(new File("15/input.txt").toPath(), Charset.defaultCharset()));

        int[][] grid = new int[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(0).length(); j++) {
                grid[i][j] = Integer.parseInt("" + lines.get(i).charAt(j));
            }
        }

        // long startTime, endTime;
        // startTime = System.nanoTime();
        long part1 = part1(grid);
        System.out.println(part1);
        // endTime = System.nanoTime();
        // System.out.println("Pt1 "+(endTime - startTime) / 1000000000.00+" seconds");
        // startTime = System.nanoTime();
        long part2 = part2(grid);
        System.out.println(part2);
        // endTime = System.nanoTime();
        // System.out.println("Pt2 "+(endTime - startTime) / 1000000000.00+" seconds");
    }
}
