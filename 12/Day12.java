import java.io.*;
import java.util.*;

public class Day12 {
    // transform puzzle input lines into a graph
    public static HashSet<Node> linesToGraph(ArrayList<String> lines) {
        // turn each line into a node connection
        HashSet<Node> nodes = new HashSet<>();

        for (String line : lines) {
            String[] parts = line.split("-");
            Node pLeft = null; // pointers
            Node pRight = null; // pointers
            for (Node n : nodes) {
                if (n.value.equals(parts[0])) {
                    pLeft = n;
                }
                if (n.value.equals(parts[1])) {
                    pRight = n;
                }
            }
            if (pLeft == null) {
                pLeft = new Node(parts[0]);
                nodes.add(pLeft);
            }
            if (pRight == null) {
                pRight = new Node(parts[1]);
                nodes.add(pRight);
            }
            pLeft.addConnection(pRight);
            pRight.addConnection(pLeft);
        }

        // for(Node n : nodes) {
        // System.out.println(n.toString());
        // }

        return nodes;
    }

    // tldr a traversal where lowercase nodes are only visited once, from start-end
    // count all possible traversals
    public static int part1(HashSet<Node> nodes) {
        // tldr do a bft from start to end, but only visit lowercase nodes once
        int count = 0;

        // find starting node
        Node start = null;
        for (Node node : nodes) {
            if (node.value.equals("start")) {
                start = node;
                break;
            }
        }

        // create path
        Deque<Node> path = new LinkedList<Node>();
        HashSet<String> seen = new HashSet<>();

        // start the path
        path.add(start);
        seen.add(start.value);

        count = recursionHelper1(nodes, start, seen);

        return count;
    }

    public static int recursionHelper1(HashSet<Node> nodes, Node start, HashSet<String> seen) {
        int count = 0;

        if (start.value.equals("end")) {
            return 1;
        }

        for (Node n : start.connections) {
            if (!seen.contains(n.value)) {
                HashSet<String> seenCopy = new HashSet<>();
                seenCopy.addAll(seen);
                if (n.isLower) {
                    seenCopy.add(n.value);
                }
                count += recursionHelper1(nodes, n, seenCopy);
            }
        }

        return count;
    }

    public static long part2(HashSet<Node> nodes) {
        // tldr do a bft from start to end, but only visit lowercase nodes once
        long count = 0;

        // find starting node
        Node start = null;
        for (Node node : nodes) {
            if (node.value.equals("start")) {
                start = node;
                break;
            }
        }

        // create path
        Deque<Node> path = new LinkedList<Node>();
        HashSet<String> seenOnce = new HashSet<>();
        HashSet<String> seenTwice = new HashSet<>();

        // start the path
        path.add(start);
        seenOnce.add(start.value);
        seenTwice.add(start.value);

        count = recursionHelper2(nodes, start, seenOnce, seenTwice);

        return count;
    }

    public static int recursionHelper2(HashSet<Node> nodes, Node start, HashSet<String> seenOnce,
            HashSet<String> seenTwice) {
        int count = 0;

        // exit cond
        if (start.value.equals("end")) {
            return 1;
        }

        for (Node n : start.connections) {
            // until we hit two items in seenTwice
            if (seenTwice.size() == 1) {
                // here's the opportunity to add to seenTwice
                if (!seenTwice.contains(n.value) && !seenOnce.contains(n.value)) { // unseen in any
                    HashSet<String> seenCopy1 = new HashSet<>();
                    seenCopy1.addAll(seenOnce);
                    if (n.isLower) {
                        seenCopy1.add(n.value);
                    }
                    count += recursionHelper2(nodes, n, seenCopy1, seenTwice);
                } else if (!seenTwice.contains(n.value)) { // seen in seenOnce, unseen in seenTwice
                    HashSet<String> seenCopy2 = new HashSet<>();
                    seenCopy2.addAll(seenTwice);
                    if (n.isLower) {
                        seenCopy2.add(n.value);
                    }
                    count += recursionHelper2(nodes, n, seenOnce, seenCopy2);
                } // otherwise seen in both
            } else {
                // here we run pt1 basically
                if (!seenOnce.contains(n.value)) {
                    HashSet<String> seenCopy1 = new HashSet<>();
                    seenCopy1.addAll(seenOnce);
                    if (n.isLower) {
                        seenCopy1.add(n.value);
                    }
                    count += recursionHelper2(nodes, n, seenCopy1, seenTwice);
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("12/input.txt");
        Scanner sc = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            lines.add(line);
        }
        sc.close();

        HashSet<Node> nodes = linesToGraph(lines);

        long startTime, endTime;
        startTime = System.nanoTime();
        int part1 = part1(nodes);
        System.out.println(part1);
        endTime = System.nanoTime();
        System.out.println(""+(endTime - startTime) / 1000000000.00+" seconds");
        startTime = System.nanoTime();
        long part2 = part2(nodes);
        System.out.println(part2);
        endTime = System.nanoTime();
        System.out.println(""+(endTime - startTime) / 1000000000.00+" seconds");
    }
}
