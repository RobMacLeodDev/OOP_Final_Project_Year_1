/*
Project Name: PROG1400 Introduction to OOP Project (Blackjack)
Programmer Name: Rob MacLeod W0513906
LMD: April 6th, 2026
This program allows the user to play through multiple hands of Blackjack, recording wins, losses, and draws.
*/
import ca.nscc.student.BlackJackGame; //16. Importing packages, Enum and others pg 9

public class OOP_Final {
    public static void main(String[] args) {
        BlackJackGame game = new BlackJackGame(); //6. The object, Intro to OOP, pg 8
        //11. also, 'new' Keyword, Intro to OOP, pg 17
        //20. also, objects live inside the heap Introduction to OOP pg 19
        game.play(); //15. Dot notation, Introduction to OOP, pg 21
    }
}
