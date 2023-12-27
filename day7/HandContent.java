package day7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HandContent implements Comparable<HandContent> {

    String cards;
    int bid;
    int type;


    public HandContent(String cards, int bid) {
        this.cards = cards;
        this.bid = bid;
        this.type = getHandTypeValue(cards);

    }

    @Override
    public int compareTo(HandContent other) {
        if (this.type > other.type) {
            return 1;
        } else if (this.type < other.type) {
            return -1;
        } else {
            char[] cards1 = this.cards.toCharArray();
            char[] cards2 = other.cards.toCharArray();
            for (int i = 0; i < cards1.length; i++) {
                if (cards1[i] != cards2[i]) {
                    return Integer.compare(secondRule(cards1[i]), secondRule(cards2[i]));
                }
            }
        }
        return 0; // all cards in two hand are equal -> impossible?;
    }

    private int getHandTypeValue(String cards) {
        int point = 0;
        //put the cards in a map
        Map<Character, Integer> typePoints = new HashMap<>();
        for (char card : cards.toCharArray()) {
            if(typePoints.containsKey(card)) {
               typePoints.put(card, typePoints.get(card) + 1);
            } else {
                typePoints.put(card, 1);
            }
        }
        //get the value based on rules
        int[] values = {0, 0, 0, 0, 0};
        for (Map.Entry<Character, Integer> entry : typePoints.entrySet()) {
            if(entry.getValue() == 1) {
                values[0]++;
                point =  firstRule(values);
            } else if (entry.getValue() == 2) {
                values[1]++;
                point =  firstRule(values);
            } else if (entry.getValue() == 3) {
                values[2]++;
                point =  firstRule(values);
            } else if (entry.getValue() == 4) {
                values[3]++;
                point =  firstRule(values);
            } else if (entry.getValue() == 5) {
                values[4]++;
                point =  firstRule(values);
            }
        }
        return point;
    }

    private int firstRule(int[] cardsOccurrence) {
        int[] fiveOfAkind = {0, 0, 0, 0, 1};
        if( Arrays.equals(cardsOccurrence, fiveOfAkind)) {
            return 7;
        }
        int[] fourOfAkind = {1, 0, 0, 1, 0};
        if( Arrays.equals(cardsOccurrence, fourOfAkind)) {
            return 6;
        }
        int[] fullHouse = {0, 1, 1, 0, 0};
        if( Arrays.equals(cardsOccurrence, fullHouse)) {
            return 5;
        }
        int[] threeOfAkind = {2, 0, 1, 0, 0};
        if( Arrays.equals(cardsOccurrence, threeOfAkind)) {
            return 4;
        }
        int[] twoPair = {1, 2, 0, 0, 0};
        if( Arrays.equals(cardsOccurrence, twoPair)) {
            return 3;
        }
        int[] OnePair = {3, 1, 0, 0, 0};
        if( Arrays.equals(cardsOccurrence, OnePair)) {
            return 2;
        }
        return 1; //if all cards are different
    }

    private int secondRule(char ch) {
        String cardOrder = "23456789TJQKA";
        char[] cardOrderArray = cardOrder.toCharArray();
        for (int i = 0; i < cardOrderArray.length; i++) {
            if(ch == cardOrderArray[i]) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "HandContent{" +
                "cards='" + cards + '\'' +
                ", bid=" + bid +
                ", type=" + type +
                '}';
    }
}
