package day4;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

    private static void readFile(String path) {
        int totalPoints = 0;
        try {
            FileInputStream fis = new FileInputStream(path);
            Scanner sc = new Scanner(fis);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
                String[] convertedSting = convertLine(line);
                int resultByLine = getMatchesNumber(convertedSting);
                System.out.println(resultByLine);
                totalPoints += resultByLine;
            }
            System.out.println(totalPoints);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static String[] convertLine (String cardSequence) {
        String[] convertedSting = new String[2];
        Pattern pattern = Pattern.compile(":\\s*(\\d+(?:\\s*\\d+)*)\\s*\\|\\s*(\\d+(?:\\s*\\d+)*)");
        Matcher matcher = pattern.matcher(cardSequence);
        String string1 = "";
        String string2 = "";
        if (matcher.find()) {
            // Extract the two sets of integers
            string1 = matcher.group(1).trim();
            string2 = matcher.group(2).trim();
        }
        convertedSting[0] = string1;
        convertedSting[1] = string2;
        return convertedSting;
    }

    private static int[] getIntArray (String string) {
        // Use a regular expression to split the string on one or more whitespaces
        String[] stringArray = string.split("\\s+");

        int[] numbers = new int[stringArray.length];
        int index = 0;
        for(String st : stringArray) {
            numbers[index] = Integer.parseInt(st);
            index++;
        }
        return numbers;
    }

    private static int getMatchesNumber(String[] convertedSting) {
        int[] winningNumbers = getIntArray(convertedSting[0]);
        System.out.println(Arrays.toString(winningNumbers));
        int[] myNumbers = getIntArray(convertedSting[1]);
        System.out.println(Arrays.toString(myNumbers));

        // Convert arrays to lists for easier intersection operation
        //https://www.javatpoint.com/how-to-compare-two-arraylist-in-java
        List<Integer> winnings = new ArrayList<>(Arrays.asList(Arrays.stream(winningNumbers)
                .boxed().
                toArray(Integer[]::new)));
        List<Integer> others = new ArrayList<>(Arrays.asList(Arrays.stream(myNumbers)
                .boxed()
                .toArray(Integer[]::new)));

        winnings.retainAll(others);
        int[] remains = winnings.stream()
                .mapToInt(item -> item.intValue())
                .toArray();
        System.out.println(Arrays.toString(remains));
        return (int) Math.pow(2, remains.length - 1);
    }

    private static void printMatrix(String[][] inputAsMatrix) {
        // Loop through all rows
        for (int i = 0; i < inputAsMatrix.length; i++) {
            System.out.println();
            // Loop through all elements of current row
            for (int j = 0; j < inputAsMatrix[0].length; j++) {
                System.out.print(inputAsMatrix[i][j] + " ");
            }
        }
    }

    public static void main(String[] args) {
        String path = "day4/four.txt";
        readFile(path);

    }
    
}
