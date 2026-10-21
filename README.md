# Blackjack Game

* * *

## The Project

This is a console-based Blackjack game written in Java, a first-year project designed to demonstrate my understanding of Object-Oriented Programming (OOP) concepts.

## Project Architecture

The main application file launches a BlackjackGame class to control the core game flow and logic. The BlackJackGame object in turn spins up a deck object to simulate the deck, two hand object to simulate both the dealer's and the players' hand, a GameSession object to track and save persistent player stats, and a UI object to display the hands and game-state in the console.

## What I Learned from this Project

This project locked in my understanding of OOP and how to build a reasonable architecture for a relatively complex program by breaking programming tasks into individual classes and roles.

I am also proud of the _handFormat()_ method inside the hand class. Printing dynamic hands of cards in ASCII characters was one of the more challenging logic puzzles I've run into so far, and I was pleased with the results.

This project also taught me how to read and write information to a file, and how Java HashMaps work. These both represented independent learning, outside of course materials

The thing I am most proud of in this project is how seamlessly the OOP architecture works and how one class object spins up all the others it needs to fulfill all game functions.

## What I would do differently

I came to this project with Python experience, and my initial thought was to use something like a Python dictionary to track the hands and the deck, using key-value pairs to track both the cards and their scoring value in one data structure. I came upon HashMaps as a Java alternative, but unfortunately, I soon discovered HashMaps don't preserve insertion order. This would cause the order of cards in a player's hand to shift at random each time the game state changed, creating an unsuitable player experience. Falling back to ArrayLists, with a HashMap as a decoder ring for scoring, was a logical workaround to get a working project completed on time, but I knowing what I know now, I would have approached this problem differently.  
  
Mixing Unicode and ASCII text in the console also caused unanticipated issues; see known issues below. I was excited to add a little visual excitement with realistic card symbols, but had I known more about how terminals display characters, I would have skipped this feature.

## File Structure

```
├── OOP_Final.java                  # Main application entry point
└── ca/
    └── nscc/
        └── student/
            ├── BlackJackGame.java  # Controls game flow and core logic
            ├── Deck.java           # Simulates a standard deck of cards
            ├── GameSession.java    # Handles persistant player stats
            ├── Hand.java           # Simulates player and dealer hands
            └── UI.java             # Handles user interface interactions
```

## Blackjack Rules

This program simulates the game of Blackjack, playing a single hand against a computer dealer. The player can then play again as many times as they wish, and the game tracks and displays wins, losses, and draws across sessions. The program shows your first two cards and the dealer's first card, then you choose to "hit" (get another card) or stand. Once you decide to stand, the dealer will draw cards until the score reaches 17 or higher. Aces are worth either 1 or 11, face cards are worth 10, and all other cards are worth their face value. The goal is to score as close to 21 as possible without going over. This simplified game does not include betting, insurance, double-downs, or splitting. Full Blackjack rules are available at: [https://www.wikihow.com/Play-Blackjack](https://www.wikihow.com/Play-Blackjack)

## Save File:

The game creates a file called "save.txt" in the main working directory and uses it to track wins, losses, and draws across sessions. To reset your progress, delete or rename this file, and a new, clean file will be created automatically.

## Known issue:

The card designs are drawn using ASCII characters, but the suit symbols (such as the heart or club symbol) are Unicode. This can cause spacing issues because Unicode symbols can take up more than a single character width. This can range from subtle variation across a large hand to very noticeable spacing issues, depending on your terminal emulator. Some terminals also cannot display Unicode and will replace the suit symbols with question marks. If either issue affects your ability to enjoy the game, open the "Hand.java" file in an IDE or editor and comment out the switch case in the _handformat()_ method (lines 75 through 87). This will prevent Unicode symbols from being used, replacing them with capital letters (e.g., "D" for diamond).