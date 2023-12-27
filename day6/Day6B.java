package day6;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6B {

    public static void readFile(String path) {
        long[][] inputMatrix = new long[2][1];
        try {
            FileInputStream fis = new FileInputStream(path);
            Scanner sc = new Scanner(fis);
            int row = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                long value = convertLine(line);
                long[] valueArray = new long[1];
                valueArray[0] = value;
                inputMatrix[row] = valueArray;
                row++;
            }
            long solution = getNumberOfOneWay(inputMatrix);
            System.out.println(solution);
        } catch (IOException e) {
            System.out.println();
        }
    }
    public static long convertLine(String line) {
        String[] format = line.split(": ");
        String valueAsString = format[1].trim().replaceAll("\\s+", "");
        System.out.println(valueAsString);
        long value = Long.parseLong(valueAsString);

        return value;
    }
    public static int getNumberOfOneWay(long[][] inputMatrix) {
        int solution = 1;
        for (int i = 0; i < inputMatrix[0].length; i++) {
            int currentDifferentWay = 0;
            long currentTime = inputMatrix[0][i];
            for(int j = 0; j <= currentTime; j++) {
                long currentDistance = (currentTime - j) * j;
                long recordDistance = inputMatrix[1][i];
                if (currentDistance > recordDistance) {
                    currentDifferentWay++;
                }
            }
            solution *= currentDifferentWay;
        }
        return solution;
    }

    public static void main(String[] args) {
        String path = "day6/six.txt";
        readFile(path);

    }
}
