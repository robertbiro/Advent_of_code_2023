package day9;

import java.io.FileInputStream;
import java.util.Scanner;

public class Day9 {

    private static void readFile (String path) {

        try {
            FileInputStream fis = new FileInputStream(path);
            Scanner sc = new Scanner(fis);
            int res = 0;
            while (sc.hasNextLine()) {
                String[] lineStr = sc.nextLine().split(" ");
                int len = lineStr.length;
                int[] sequence = new int[len];
                for (int i = 0; i < len; i++) {
                    sequence[i] = Integer.parseInt(lineStr[i]);
                }
                //to retrieve the first item of solution from the input line
                res += sequence[sequence.length - 1];
                //to create the first array which contain the difference
                int[] differences = partSolution(sequence);
                //to retrieve the second item of solution from the first difference sequence
                res += differences[differences.length - 1];

                while (!allItemIsNull(differences)) {
                    differences = partSolution(differences);
                    //to retrieve the remaining item of solution from other sequence
                    res += differences[differences.length - 1];
                }
            }
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int[] partSolution (int[] sequences) {
        int[] differences = new int[sequences.length - 1];
        for (int i = 0; i < sequences.length - 1; i++) {
            differences[i] = sequences[i + 1] - sequences[i];
        }
        return differences;
    }

    private static boolean allItemIsNull(int[] sequences) {
        for (int item : sequences) {
            if (item != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String path = "day9/nine.txt";
        readFile(path);
    }
}
