package day2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Day2 {

    public static void readFile() {
        int result = 0;
        try {
            String path = "day2/two.txt";
            FileInputStream fis=new FileInputStream(path);
            Scanner sc=new Scanner(fis);
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] games = line.split(": ");
                int index = Integer.parseInt(games[0].split(" ")[1]);
                String[] oneGame = games[1].split("; ");
                boolean lineValidation = getResult(oneGame);
                if(lineValidation) {
                    result += index;
                }
            }
            //closes the scanner
            sc.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    public static boolean getResult(String[] oneGame) {
        int result = 0;
        Integer red = 12;
        Integer green = 13;
        Integer blue =  14;
       boolean valid = false;
       outer:
        for (String cubes : oneGame) {
            String[] cubeInCubes = cubes.split(", ");
            for (String cube : cubeInCubes) {
                String[] colorQuantity = cube.split(" ");
                //System.out.println(colorQuantity[1] + ": " + colorQuantity[0]);
                int value = Integer.parseInt(String.valueOf(colorQuantity[0]));
                if (String.valueOf(colorQuantity[1]).equals("red") && value <= red ||
                        String.valueOf(colorQuantity[1]).equals("green") && value <= green ||
                        String.valueOf(colorQuantity[1]).equals("blue") && value <= blue) {
                    //System.out.println(true);
                    valid = true;
                } else {
                    valid = false;
                    break outer;
                }
            }
        }
            return valid;
        }

    public static void main(String[] args) {
        readFile();
    }

}
