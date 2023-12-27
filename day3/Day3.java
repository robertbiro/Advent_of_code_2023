package day3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Day3 {

    private static void readFile(String path){
        try {
            FileInputStream fis = new FileInputStream(path);
            Scanner sc = new Scanner(fis);
            // Determine the number of rows-------------------------------
            int rows = 0;
            while (sc.hasNextLine()) {
                sc.nextLine();
                rows++;
            }
            // Create 2D char matrix-------------------------------------
            char[][] inputAsMatrix = new char[rows][];
            sc.close();

            // Reopen the file input stream and scanner------------------
            fis = new FileInputStream(path);
            sc = new Scanner(fis);

            // Populate the matrix------------------------------------
            int currentRow = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
                inputAsMatrix[currentRow++] = line.toCharArray();
            }
            // Close the resources-----------------------------------
            sc.close();
            fis.close();
            check(inputAsMatrix);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void check(char[][] inputAsMatrix) {
        int result = 0;
        for (int i = 0; i < inputAsMatrix.length; i++) {
            for (int j = 0; j < inputAsMatrix[0].length; j++) {
                char currentChar = inputAsMatrix[i][j];
                //if char is digit, join them.
                if (Character.isDigit(currentChar)) {
                    StringBuilder number = new StringBuilder();
                    int endOfNumber = j;
                    while (Character.isDigit(inputAsMatrix[i][endOfNumber])) {
                        number.append(inputAsMatrix[i][endOfNumber]);
                        if (endOfNumber + 1 < inputAsMatrix[0].length) {
                            endOfNumber++;
                        } else {
                            break;
                        }
                    }
                    //check the surrounding of joined number -> the number is a partNumber?
                    if(isPartNumber(inputAsMatrix, i, j, endOfNumber)) {
                        System.out.println(number);
                        result += Integer.parseInt(number.toString());
                    }
                    //move the "j" index to the end of joined number
                    j = endOfNumber;
                    }
                }
            }

        System.out.println(result);
    }


    public static boolean isPartNumber(char[][] inputAsMatrix, int row, int startOfNumber, int endOfNumber) {

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        StringBuilder isPartNumber = new StringBuilder();
        while (startOfNumber < endOfNumber) {
            for (int i = 0; i < 8; i++) {
                int newRow = row + dx[i];
                int newCol = startOfNumber + dy[i];

                if (isValidCoordinate(inputAsMatrix, newRow, newCol)) {
                    char symbol = inputAsMatrix[newRow][newCol];
                    if (symbol != '.' && !Character.isDigit(symbol)) {
                        //if the neighbourhood item is not '.' and not digit, then append "1".
                        //if the isPartNumber length is bigger than 0 -> it is a valid partNumber.
                        isPartNumber.append("1");
                    }
                }
            }
            startOfNumber++;
        }
        return isPartNumber.length() > 0;
    }

    public static boolean isValidCoordinate(char[][] inputAsMatrix, int row, int col) {
        return row >= 0 && row < inputAsMatrix.length && col >= 0 && col < inputAsMatrix[0].length;
    }

    private static void printMatrix(List<char[]> inputAsMatrix) {
        // Loop through all rows
        for (int i = 0; i < inputAsMatrix.size(); i++) {
            System.out.println();
            // Loop through all elements of current row
            for (int j = 0; j < inputAsMatrix.get(i).length; j++) {
                System.out.print(inputAsMatrix.get(i)[j] + " ");
            }
        }
    }

    public static void main(String[] args) {
        String path = "day3/three.txt";
       readFile(path);
    }
}
