package day1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class firstDayB {

    public static void readFile() {
        try {
            String path = "day1/sample.txt";
            String[] numbersAsString = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
            FileInputStream fis=new FileInputStream(path);
            Scanner sc=new Scanner(fis);
            int result = 0;
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                for (String item : numbersAsString) {
                     line = line.replace(item, String.valueOf(Arrays.asList(numbersAsString).indexOf(item) + 1));
                }
                char[] lineChar = line.toCharArray();
                String stingAsNumber = "";
                for(char ch : lineChar) {
                    if(Character.isDigit(ch)) {
                        stingAsNumber += ch;
                    }
                }
                System.out.println(line);
                System.out.println(getIntByLine(stingAsNumber));
                result += getIntByLine(stingAsNumber);
            }
            //closes the scanner
            sc.close();
            System.out.println(result);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static int getIntByLine(String st) {
        int twoDigitNumber = 0;
        String firstDigit = String.valueOf(st.charAt(0));
        String secondDigit = String.valueOf(st.charAt(st.length() - 1));
        String joined = firstDigit.concat(secondDigit);
        twoDigitNumber = Integer.parseInt(joined);
        return twoDigitNumber;

    }

    public static void main(String[] args) {
        readFile();

    }
}
