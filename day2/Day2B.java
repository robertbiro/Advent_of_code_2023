package day2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Day2B {

    public static void readFile() {
        int allResult = 0;
        try {
            String path = "day2/two.txt";
            FileInputStream fis=new FileInputStream(path);
            Scanner sc=new Scanner(fis);
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] games = line.split(": ");
                String[] oneGame = games[1].split("; ");
                int resultByLine = getResult(oneGame);
                allResult += resultByLine;
            }
            //closes the scanner
            sc.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println(allResult);
    }

    public static int getResult(String[] oneGame) {
        int result = 0;
        int redMax = 0;
        int greenMax = 0;
        int blueMax =  0;
        for (String cubes : oneGame) {
            String[] cubeInCubes = cubes.split(", ");
            for (String cube : cubeInCubes) {
                String[] colorAndQuantity = cube.split(" ");
                String color = String.valueOf(colorAndQuantity[1]);
                int value = Integer.parseInt(String.valueOf(colorAndQuantity[0]));
                if (color.equals("red") && value > redMax) {
                    redMax = value;
                } else if (color.equals("green") && value > greenMax ) {
                    greenMax = value;
                } else if (color.equals("blue") && value > blueMax) {
                    blueMax = value;
                }
            }
            result = redMax * greenMax * blueMax;
        }
        return result;
    }

    public static void main(String[] args) {
        readFile();
    }
}
