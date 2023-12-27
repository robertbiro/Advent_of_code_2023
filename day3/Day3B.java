package day3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Day3B {

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
            Item[][] inputAsMatrix= new Item[rows][];
            //char[][] inputAsMatrix = new char[rows][];
            sc.close();

            // Reopen the file input stream and scanner------------------
            fis = new FileInputStream(path);
            sc = new Scanner(fis);

            // Populate the matrix---------------------------------------
            int currentRow = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
                //convert char in line to Item type
                Item[] col = new Item[line.length()];
                int indexCol = 0;
                for(char ch : line.toCharArray()) {
                    Item item = new Item(ch);
                    col[indexCol++] = item;
                }
                inputAsMatrix[currentRow++] = col;
            }
            // Close the resources-------------------------------------
            sc.close();
            fis.close();
            check(inputAsMatrix);
            for(int i = 0; i < inputAsMatrix.length; i++) {
                for (int j = 0; j < inputAsMatrix[0].length; j++) {
                    System.out.print(inputAsMatrix[i][j].getSymbol());
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void check(Item[][] inputAsMatrix) {
        int result = 0;
        for (int i = 0; i < inputAsMatrix.length; i++) {
            for (int j = 0; j < inputAsMatrix[0].length; j++) {
                Item currentChar = inputAsMatrix[i][j];
                if (currentChar.getSymbol() == '*') {
                    int row = i;
                    int col = j;

                    //check the surrounding of joined number -> the number is a partNumber?

                    //move the "j" index to the end of joined number

                }
            }
        }

        System.out.println(result);
    }


    public static boolean isGearNumber(Item[][] inputAsMatrix, int row, int col) {
        //except the same column of '*' ->
        int[] dx = {-1, -1, 0, 0, 1, 1};
        int[] dy = {-1, 1, -1, 1, -1, 1};


        for (int i = 0; i < 8; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];

            if (isValidCoordinate(inputAsMatrix, newRow, newCol)) {
                Item symbol = inputAsMatrix[newRow][newCol];
                //if char (symbol) is digit:
                if (Character.isDigit(symbol.getSymbol())) {
                    StringBuilder isGearNumber = new StringBuilder();
                    //check to left direction -> is it digit?
                    int movingColToLeft = newCol - 1;
                    while (isValidCoordinate(inputAsMatrix, newRow, movingColToLeft) &&
                            Character.isDigit(inputAsMatrix[newRow][movingColToLeft].getSymbol())) {
                        movingColToLeft--;
                        //if the neighbourhood item is digit, append "1".
                        //if the isGearNumber length is bigger than 0 -> it is a valid partNumber.
                        isGearNumber.insert(isGearNumber.length() - 1, inputAsMatrix[newRow][movingColToLeft]);
                    }
                    int movingColToRight = newCol + 1;
                    while (isValidCoordinate(inputAsMatrix, newRow, movingColToRight) &&
                            Character.isDigit(inputAsMatrix[newRow][movingColToRight].getSymbol())
                    ) {
                        movingColToRight++;
                        //if the neighbourhood item is digit, append "1".
                        //if the isGearNumber length is bigger than 0 -> it is a valid partNumber.
                        isGearNumber.append(inputAsMatrix[newRow][movingColToRight]);
                    }
                }
            }
        }
        return true;
    }

    public static boolean isValidCoordinate(Item[][] inputAsMatrix, int row, int col) {
        return row >= 0 && row < inputAsMatrix.length && col >= 0 && col < inputAsMatrix[0].length;
    }


    public static void main(String[] args) {
        String path = "day3/three.txt";
        readFile(path);
    }
}
