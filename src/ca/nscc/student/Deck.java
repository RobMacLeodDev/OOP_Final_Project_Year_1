package ca.nscc.student;

import java.util.Random;
import java.util.ArrayList;

public class Deck { //5. The class, Intro to OOP, pg 7
    //fields
    private ArrayList<String> deck = new ArrayList<>(); //List of strings to store cards in the deck

    //constructor
    public Deck() {
        shuffleDeck();
    }

    //methods
    public String dealCard() {
        // simulates dealing a card from the deck
        Random rand = new Random();
        int draw = rand.nextInt(deck.size());
        String card = deck.get(draw);
        deck.remove(draw); // remove the card so it isn't drawn again until shuffled
        return card;
    }

    // clears the deck if needed, rebuilds a fresh 52 card deck
    public void shuffleDeck() {
        deck.clear();
        //combines arrays of various deck elements to build an arraylist for the deck.
        String[] suits = {"H", "D", "C", "S"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                deck.add(suit + ranks[i]);
            }
        }
    }

}

