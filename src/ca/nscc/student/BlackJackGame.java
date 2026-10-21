package ca.nscc.student; //17. creating a package, Enum and others pg 8
//18 also, Naming conventions packages Enum and others pg 7

public class BlackJackGame {
    // fields
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;
    private GameSession session;
    private UI ui;

    //constructor
    public BlackJackGame() {
        this.deck = new Deck(); //12. Composition, Aggregation and Composition, page 8
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
        this.session = new GameSession();
        this.ui = new UI();
    }

    // methods
    public void dealInitialCards() { //1. public visibility,Encapsulation and Data Integrity pg 4
        //deal cards at the start of the game)
        for (int i = 1; i <= 2; i++) {
            dealerHand.addCard(deck.dealCard());
            playerHand.addCard(deck.dealCard());
        }
    }

    public String compareHands() {
        //Handles hand score comparison logic
        if ((playerHand.getScore() > 21) && (dealerHand.getScore() > 21)) {
            session.recordLoss();
            return "Bust! You Lose."; //if both player and dealer ever go over 21, player busts
        } else if (playerHand.getScore() == dealerHand.getScore()) {
            session.recordDraw();
            return "Push! The hand is a draw.";
        } else if (dealerHand.getScore() == 0) {
            session.recordLoss();
            return "You lose, the dealer has Blackjack!";
        } else if (playerHand.getScore() == 0) {
            session.recordWin();
            return "You win with a Blackjack!";
        } else if (playerHand.getScore() > 21) {
            session.recordLoss();
            return "Bust! You lose.";
        } else if (dealerHand.getScore() > 21) {
            session.recordWin();
            return "The dealer busts! You win.";
        } else if (playerHand.getScore() > dealerHand.getScore()) {
            session.recordWin();
            return String.format("You win, with your %d beating the dealer's %d.%n",
                    playerHand.getScore(), dealerHand.getScore());
        } else {
            session.recordLoss();
            return String.format("You lose, with the dealer's %d beating your %d.%n",
                    dealerHand.getScore(), playerHand.getScore());
        }
    }

    public void playerTurn() {
        //Handle's the player's turn
        while (true) {
            int score = playerHand.getScore();
            if (score == 0 || score > 21) break;  // blackjack or bust
            //if the player doesn't draw another card, turn ends
            if (!ui.yesNo("Type 'y' to get another card, type 'n' to pass: ")) break;
            ui.clearScreen();
            playerHand.addCard(deck.dealCard());
            //clear the screen and display updated hand after each new card
            displayHands(playerHand, dealerHand, false);
        }
    }

    public void dealerTurn() {
        //on dealer's turn, draws up to 17 or higher
        while (dealerHand.getScore() < 17) {
            dealerHand.addCard(deck.dealCard());
        }
    }
    //Uses the UI object to display the ascii cards and scores
    public void displayHands(Hand playerHand, Hand dealerHand, boolean revealDealer) {
        ui.displayHand(playerHand.handFormat());
        ui.displayResult(String.format("Your score: %d%n", playerHand.getScore()));
        if (revealDealer) {
            ui.displayHand(dealerHand.handFormat());
            ui.displayResult(String.format("Dealer score: %d%n", dealerHand.getScore()));

        } else {
            ui.displayResult(String.format("Dealer's first card:%n"));
            ui.displayHand(dealerHand.getFirstCard());
        }
    }

    //main game loop logic
    public void play() {
        while (true) {
            ui.printLogo();
            ui.displayResult(session.getStats());
            dealInitialCards();
            if (dealerHand.getScore() == 0) { //if dealer Blackjack, skip whole hand.
                displayHands(playerHand, dealerHand, true);
            } else {
                displayHands(playerHand, dealerHand, false);  // hide dealer's second card
                playerTurn();
                dealerTurn();
                ui.clearScreen();
                ui.displayResult("Final Results:");
                ui.displayResult("");
                displayHands(playerHand, dealerHand, true);   // reveal everything
            }
            String result = compareHands();
            ui.displayResult(result);
            ui.displayResult(session.getStats());

            if (ui.yesNo("Play another hand? (y/n): ")) {
                //reset deck and hands, clear the screen, and go to start of game loop
                ui.clearScreen();
                playerHand.clear();
                dealerHand.clear();
                deck.shuffleDeck();
            } else {
                session.saveData(); //save data before exit
                ui.closeScanner(); //clean up by closing the UI scanner before exit
                break;
            }
        }
    }
}
