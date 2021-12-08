#!/bin/bash
# script for starting a new day- making new .java and input files

# prompt day
echo "Day num"
read day

mkdir $day

touch $day/Day$day.java
echo "import java.io.*;
import java.util.*;

public class Day"$day" {
    public static int part1(ArrayList<String> lines) {
        return 0;
    }

    public static long part2(ArrayList<String> lines) {
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File(\""$day"/input.txt\");
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
}" > $day/Day$day.java

touch $day/input.txt