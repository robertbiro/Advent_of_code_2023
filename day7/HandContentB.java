package day7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HandContentB implements Comparable<HandContentB> {

    String cards;
    int bid;
    int type;


    public HandContentB(String cards, int bid) {
        this.cards = cards;
        this.bid = bid;
        this.type = getHandTypeValue(cards);

    }

    @Override
    public int compareTo(HandContentB other) {
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
        int[] values = {0, 0, 0, 0, 0, 0};
        for (Map.Entry<Character, Integer> entry : typePoints.entrySet()) {
            if(entry.getValue() == 1 && entry.getValue() != 'J') {
                values[0]++;
                point =  firstRule(values);
            } else if (entry.getValue() == 2 && entry.getValue() != 'J') {
                values[1]++;
                point =  firstRule(values);
            } else if (entry.getValue() == 3 && entry.getValue() != 'J') {
                values[2]++;
                point =  firstRule(values);
            } else if (entry.getValue() == 4 && entry.getValue() != 'J') {
                values[3]++;
                point =  firstRule(values);
            } else if (entry.getValue() == 5 && entry.getValue() != 'J') {
                values[4]++;
                point =  firstRule(values);
            } else if(entry.getValue() == 'J') {
                values[5]++;
            }
        }
        return point;
    }

    private int firstRule(int[] cardsOccurrence) {
        int[] fiveOfAkind = {0, 0, 0, 0, 1, 0};
        if( Arrays.equals(cardsOccurrence, fiveOfAkind) && cardsOccurrence[5] == 0) {
            return 7;
        }
        int[] fourOfAkind = {1, 0, 0, 1, 0, 0};
        if( Arrays.equals(cardsOccurrence, fourOfAkind) && cardsOccurrence[5] == 0) {
            return 6;
        }
        int[] fullHouse = {0, 1, 1, 0, 0, 0};
        if( Arrays.equals(cardsOccurrence, fullHouse) && cardsOccurrence[5] == 0) {
            return 5;
        }
        int[] threeOfAkind = {2, 0, 1, 0, 0, 0};
        if( Arrays.equals(cardsOccurrence, threeOfAkind) && cardsOccurrence[5] == 0) {
            return 4;
        }
        int[] twoPair = {1, 2, 0, 0, 0, 0};
        if( Arrays.equals(cardsOccurrence, twoPair) && cardsOccurrence[5] == 0) {
            return 3;
        }
        int[] OnePair = {3, 1, 0, 0, 0, 0};
        if( Arrays.equals(cardsOccurrence, OnePair) && cardsOccurrence[5] == 0) {
            return 2;
        } if (cardsOccurrence[5] != 0) {

        }
        return 1; //if all cards are different
    }

    private int secondRule(char ch) {
        String cardOrder = "J23456789TQKA";
        char[] cardOrderArray = cardOrder.toCharArray();
        for (int i = 0; i < cardOrderArray.length; i++) {
            if(ch == cardOrderArray[i]) {
                return i;
            }
        }
        return 0;
    }

    private int JokerRule(int[] values) {
        int jokerNum = values[5];
        for (int i = values.length - 2; i >= 0; i--) {
            while (jokerNum != 0) {
                if(values[3] == 1) {
                    values[4] = 1;
                    values[3] = 0;
                    jokerNum--;
                }
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
