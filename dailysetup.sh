#!/bin/bash
# script for starting a new day- making new .java and input files

# prompt day
echo "Day num"
read day

mkdir $day

touch $day/Day$day.java
echo "import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class Day"$day" {
    public static int part1(ArrayList<String> lines) {
        return 0;
    }

    public static long part2(ArrayList<String> lines) {
        return 0;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(new File(\""$day"/input.txt\").toPath(), Charset.defaultCharset()));

        // long startTime, endTime;
        // startTime = System.nanoTime();
        int part1 = part1(lines);
        System.out.println(part1);
        // endTime = System.nanoTime();
        // System.out.println(\"Pt1 \"+(endTime - startTime) / 1000000000.00+\" seconds\");
        // startTime = System.nanoTime();
        long part2 = part2(lines);
        System.out.println(part2);
        // endTime = System.nanoTime();
        // System.out.println(\"Pt2 \"+(endTime - startTime) / 1000000000.00+\" seconds\");
    }
}" > $day/Day$day.java

touch $day/input.txt