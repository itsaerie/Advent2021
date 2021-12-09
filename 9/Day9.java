import java.io.*;
import java.util.*;
import java.awt.Point;

public class Day9 {
    public static int part1(int[][] graph) {
        int sum = 0;

        for(int i=0; i<graph.length; i++) {
            for(int j=0; j<graph[0].length; j++) {
                boolean isLowest = true;
                // check adjacencies
                if(i>0) {
                    if(graph[i][j] >= graph[i-1][j]) {
                        isLowest = false;
                    }
                }
                if(i<graph.length-1) {
                    if(graph[i][j] >= graph[i+1][j]) {
                        isLowest = false;
                    }
                }
                if(j>0) {
                    if(graph[i][j] >= graph[i][j-1]) {
                        isLowest = false;
                    }
                }
                if(j<graph[0].length-1) {
                    if(graph[i][j] >= graph[i][j+1]) {
                        isLowest = false;
                    }
                }

                if(isLowest) {
                    sum+=graph[i][j]+1;
                }
            }
        }

        return sum;
    }

    public static long part2(int[][] graph) {
        boolean[][] traversed = new boolean[graph.length][graph[0].length];
        for(boolean[] row : traversed) {
            Arrays.fill(row, false);
        }

        long[] largest = new long[4];

        for(int i=0; i<graph.length; i++) {
            for(int j=0; j<graph[0].length; j++) {
                // traverse through graph using deque
                Deque<Point> toTravel = new LinkedList<>();
                toTravel.add(new Point(i,j));
                int basinSize = 0;
                if(!traversed[i][j] && graph[i][j] != 9) {
                    // traverse
                    while(!toTravel.isEmpty()) {
                        Point p = toTravel.pop();
                        if(traversed[p.x][p.y]) {
                            continue;
                        }
                        // traverse current point
                        traversed[p.x][p.y] = true;
                        if(graph[p.x][p.y] == 9) {
                            continue;
                        } else {
                            basinSize++;
                        }
                        // find adjacent points
                        if(p.x>0 && !traversed[p.x-1][p.y]) {
                            toTravel.add(new Point(p.x-1,p.y));
                        }
                        if(p.y>0 && !traversed[p.x][p.y-1]) {
                            toTravel.add(new Point(p.x,p.y-1));
                        }
                        if(p.x<graph.length-1 && !traversed[p.x+1][p.y]) {
                            toTravel.add(new Point(p.x+1, p.y));
                        }
                        if(p.y<graph[0].length-1 && !traversed[p.x][p.y+1]) {
                            toTravel.add(new Point(p.x, p.y+1));
                        }
                    }
                }

                largest[0] = basinSize;
                Arrays.sort(largest);
            }
        }

        return largest[1]*largest[2]*largest[3];
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("9/input.txt");
        Scanner sc = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            lines.add(line);
        }
        sc.close();

        // turn them into 2D array
        int[][] graph = new int[lines.size()][lines.get(0).length()];
        for(int i=0; i< lines.size(); i++) {
            for(int j=0; j< lines.get(i).length(); j++) {
                graph[i][j] = Integer.parseInt(""+lines.get(i).charAt(j));
            }
        }
        long startTime, endTime;

        startTime = System.nanoTime();
        int part1 = part1(graph);
        System.out.println(part1);
        endTime = System.nanoTime();
        System.out.println(""+(endTime - startTime) / 1000000000.00+" seconds");
        startTime = System.nanoTime();
        long part2 = part2(graph);
        System.out.println(part2);
        endTime = System.nanoTime();
        System.out.println(""+(endTime - startTime) / 1000000000.00+" seconds");
    }
}
