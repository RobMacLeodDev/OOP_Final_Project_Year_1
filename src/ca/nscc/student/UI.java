package ca.nscc.student;

import java.util.Scanner;

public class UI {
    //fields
    private String logo = """
            .------.            _     _            _    _            _
            |A_  _ |.          | |   | |          | |  (_)          | |
            |( \\/ ).-----.     | |__ | | __ _  ___| | ___  __ _  ___| | __
            | \\  /|K /\\  |     | '_ \\| |/ _` |/ __| |/ / |/ _` |/ __| |/ /
            |  \\/ | /  \\ |     | |_) | | (_| | (__|   <| | (_| | (__|   <
            `-----| \\  / |     |_.__/|_|\\__,_|\\___|_|\\_\\ |\\__,_|\\___|_|\\_\\
                  |  \\/ K|                            _/ |
                  `------'                           |__/
            """;

    private Scanner input = new Scanner(System.in);


    //constructor
    public UI() {
    }

    //methods
    // Method to clear the screen by printing a bunch of blank lines
    public void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    // method to ask the user a yes/no question
    public boolean yesNo(String message) {
        System.out.print(message);
        // 14. method variables live inside the stack Introduction to OOP pg 19
        char userChar; //variable to hold the first char entered
        boolean answer = false; //default answer is "no" just in case---> no infinite loops
        boolean proceed = true; //keeps asking until the user provides a valid result
        do {
            String response = input.nextLine();
            try {
                 userChar = response.charAt(0);
            } catch (Exception e) {
                userChar = 'e'; //if user just hit enter, passes 'e' into loop so they are reprompted
            }
            if ((userChar == 'y') || (userChar == 'Y')) {
                answer = true;
                proceed = false;
            } else if ((userChar == 'n') || (userChar == 'N')) {
                proceed = false; //answer variable already defaults to false
            } else
                System.out.print("Please enter a 'y' or a 'n': ");
        } while (proceed);
        return answer;
    }

    // used to print text to screen
    public void displayResult(String result) {
        System.out.println(result);
    }

    // used to display a hand on the screen)
    public void displayHand(String[] hand) {
        for (String line : hand) {
            System.out.println(line);
        }
    }

    //prints the logo
    public void printLogo() {
        displayResult(logo);
    }

    // this runs last thing when the program exits to close the scanner gracefully
    public void closeScanner() {
        input.close();
    }

}

