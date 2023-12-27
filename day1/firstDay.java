package day1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class firstDay {

    public static void readFile() {
        try {
            String path = "day1/first.txt";
            FileInputStream fis=new FileInputStream(path);
            Scanner sc=new Scanner(fis);
            int result = 0;
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                char[] lineChar = line.toCharArray();
                String stingAsNumber = "";
                for(char ch : lineChar) {
                    if(Character.isDigit(ch)) {
                        stingAsNumber += ch;
                    }
                }
                result += getIntByLine(stingAsNumber);
                System.out.println(line);
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
