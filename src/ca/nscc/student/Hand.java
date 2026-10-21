package ca.nscc.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Hand {
    // fields
    private List<String> playerHand = new ArrayList<>(); //expandable list to hold the hand
    // 13. HashMaps live inside the heap Introduction to OOP pg 19
    private final HashMap<String, Integer> cardValues = new HashMap<>(); //key / value data to look up a card score

    // constructor
    public Hand() {
        String[] suits = {"H", "D", "C", "S"}; //suits
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"}; //ranks
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11}; //score value
        //builds a HashMap to look up card score values when constructed
        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                cardValues.put(suit + ranks[i], values[i]);
            }
        }
    }

    // methods
    // adds a card to the hand
    public void addCard(String card) {
        playerHand.add(card);
    }

    // clears the hand
    public void clear() {
        playerHand.clear();
    }

    //return the first formated card of a hand
    public String[] getFirstCard() {
        String[] deck = handFormat();
        String[] firstCard = {"", "", "", "", "", "", ""}; //first card goes here
        for (int i=0; i< deck.length; i++) {
            String line = (String.format("%s",(deck[i].substring(0, 13))));
            firstCard[i] += line;
        }
        return firstCard;
    }

    //formats cards into an ascii drawing.
    public String[] handFormat() {
        String[] formatedHand = {"", "", "", "", "", "", ""}; //final card hand goes here
        String[] asciiTemplate = { //template string array for each card
                "+-----------+",
                "|__ S       |",
                "|           |",
                "|     S     |",
                "|           |",
                "|      S __ |",
                "+-----------+"
        };
        //Symbols for the suits. Unicode, so may affect spacing or not display in some terminals
        String spade = "♠";
        String heart = "♥";
        String diamond = "♦";
        String club = "♣";
        // Pull the suit and rank out of the card string entry and assigns them to variables
        for (String card : playerHand) {
            String suit = String.format("%c", card.charAt(0));
            String rank = "";
            for (int i = 1; i < card.length(); i++) {
                rank += String.format("%c", card.charAt(i));
            }
            if (rank.length() < 2) {  //pad non-10 ranks for even spacing
                rank += " ";
            }
            //applies the Unicode symbol. Comment this switch case out if they cause formating issues in your terminal
            switch (suit) {
                case "H":
                    suit = heart;
                    break;
                case "D":
                    suit = diamond;
                    break;
                case "C":
                    suit = club;
                    break;
                default:
                    suit = spade; // makes sure all cards have a suit
            }
            //add rank and suit, combine multiple cards into a single string array
            for (int i = 0; i < asciiTemplate.length; i++) {
                String line = asciiTemplate[i];
                //replace any "__" with rank, any "S" with suit
                line = line.replace("__", rank).replace("S", suit);
                formatedHand[i] += String.format("%s     ", line);
            }
        }
        return formatedHand;
    }


    public int getScore() {
        // calculates the score of a hand.  Returns 0 if initial draw is a blackjack to signal an auto-win
        int score = 0;
        for (int i = 0; i < playerHand.size(); i++) {
            score += getValue(playerHand.get(i));
        }
        if ((score == 21) && (playerHand.size() == 2)) {
            score = 0;
        }
        //Changes value of ace(s) from 11 to 1 while score is over 21
        for (String card : playerHand) {
            if ((score > 21) && (card.charAt(1) == 'A')) {
                score -= 10;
            }
        }
        return score;
    }


    public int getValue(String card) {
        //Returns the value of a card by key
        return cardValues.get(card);
    }

}
