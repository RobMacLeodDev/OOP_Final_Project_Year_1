package ca.nscc.student;

//import what is needed to manage the save file
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class GameSession {//9. PascalCase, Intro to OOP, pg 16
    // Fields
    private int wins; //2. private visibility,Encapsulation and Data Integrity pg 4
    private int losses; //7. Fields, Intro to OOP, pg 12
    private int draws; // 19. fields outside of methods live inside the object's memory on the heap
    // Introduction to OOP pg 19
    private File save;
    private int[] stats = new int[3]; //used to store stats to make save/load easier

    //constructor
    public GameSession() {  //3. Constructor,Encapsulation and Data Integrity pg 17
        this.save = new File("save.txt");
        if (save.exists()) {//tests if the file is created yet, if so, loads the data
            try {
                Scanner scanner = new Scanner(save);
                //iterates through the file and gathers the three numbers
                int i = 0;

                while (scanner.hasNextInt() && i < 3) {
                    stats[i] = scanner.nextInt();
                    i++;
                }
                scanner.close();
                //Assign the numbers in the file to wins, loses, draws
                this.wins = stats[0];
                this.losses = stats[1];
                this.draws = stats[2];
            } catch (Exception e) { //any read error, just start fresh
                this.wins = 0; //4. "this" keyword, Encapsulation and Data Integrity pg 18
                this.losses = 0;
                this.draws = 0;
            }
        } else { //if no save file, everything starts at 0
            this.wins = 0;
            this.losses = 0;
            this.draws = 0;
        }
    }

    //methods
    //These next three methods just record a win / loss / draw
    public void recordWin() {
        wins++;
    } //8. Methods, Intro to OOP, pg 14

    public void recordLoss() {
        losses++;
    } //10. camelCase, Intro to OOP, pg 16

    public void recordDraw() {
        draws++;
    }

    //returns a formated string showing current stats
    public String getStats() {
        return String.format("Your current stats: %d win(s), %d loss(es), %d draw(s)%n",
                wins, losses, draws);
    }

    // a method to save stats to a file, to be called when the game ends
    public void saveData() {
        try { //FileWriter automatically creates the file if not present
            FileWriter writeSave = new FileWriter("save.txt");
            writeSave.write(String.format("%d %d %d", wins, losses, draws));
            writeSave.close();
        } catch (IOException e) {// generic error if save fails
            System.out.println("Error: your stats could not be saved.");
        }
    }

}
