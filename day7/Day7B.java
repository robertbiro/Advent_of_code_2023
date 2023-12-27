package day7;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day7B {

    public static void readFile(String path) {
        List<HandContent> handContents = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(path);
            Scanner sc = new Scanner(fis);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" ");
                String cards = parts[0];
                int bid = Integer.parseInt(parts[1]);
                handContents.add(new HandContent(cards, bid));

            }
            // Close the resources-----------------------------------
            sc.close();
            fis.close();
            sortCollection(handContents);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sortCollection(List<HandContent> handContents) {
        long sum = 0;
        Collections.sort(handContents);
        int rank = 0;
        for(HandContent item : handContents) {
            rank++;
            System.out.println(item);
            System.out.println(rank);
            int sumOfOnCard = rank * item.bid;
            System.out.println(sumOfOnCard);
            sum += sumOfOnCard;
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        String path = "day7/seven.txt";;
        readFile(path);

    }
}
