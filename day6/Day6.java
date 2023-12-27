package day6;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6 {

    public static void readFile(String path) {
        int[][] inputMatrix = new int[2][];
        try {
            FileInputStream fis = new FileInputStream(path);
            Scanner sc = new Scanner(fis);
            int row = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                int[] intArray = convertLine(line);
                inputMatrix[row] = intArray;
                row++;
            }
            int solution = getNumberOfOneWay(inputMatrix);
            System.out.println(solution);
        } catch (IOException e) {
            System.out.println();
        }
    }
    public static int[] convertLine(String line) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(line);
        List<Integer> integers = new ArrayList<>();
        while (matcher.find()) {
            integers.add(Integer.parseInt(matcher.group()));
        }
        int[] intArray = integers.stream()
                .mapToInt(integer -> integer.intValue())
                .toArray();
        /*
        String[] values = input.replace("Time:", "").trim().split("\\s+");

        int[] intArray = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            intArray[i] = Integer.parseInt(values[i]);
        }

        * */
        return intArray;
    }
    public static int getNumberOfOneWay(int[][] inputMatrix) {
        int solution = 1;
        for (int i = 0; i < inputMatrix[0].length; i++) {
            int currentDifferentWay = 0;
            int currentTime = inputMatrix[0][i];
            for(int j = 0; j <= currentTime; j++) {
                int currentDistance = (currentTime - j) * j;
                int recordDistance = inputMatrix[1][i];
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
