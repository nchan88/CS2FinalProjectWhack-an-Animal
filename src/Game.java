import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
//5/12/23
//A game by Nathan Chan and the culmination of countless hours of work and innovating and learning over the past two years.
//I proudly present Whack-an-Animal
//The only rule is don't touch the bombs.
public class Game implements ActionListener {
    private GameViewer window;
    private static final int SLEEP_TIME = 110;
    private ArrayList<Animal> animals;
    private int round;
    private double speedFactor;
    //Used for calculating start/stop time
    private double starttime;
    private double endtime;
    private boolean gameOver;
    //This int keeps track of how many animals are generated per round.
    private int animalCount;
    public Game() {
        // Initialize the View, then create all the Animals:
        animals = new ArrayList<Animal>();
        this.window = new GameViewer(this);
        run();

    }
    public void run() {
        //Acts like an extension of my constructor, however is specialized to reset variables specific to a game.
        speedFactor = 1.0;
        animalCount = 5;
        round = 1;
        starttime = 0;
        endtime = 0;
        gameOver = false;
        starttime = System.currentTimeMillis();
        simulateRound(animalCount, speedFactor);
    }
    //I have the variable GameOver in this class because I want it to be easily set to false when a new round is started
    //but also be able to be set to false once a user finishes the game.
    public boolean getGameOver() {
        return gameOver;
    }
    //The following two methods are required for giving the parameters needed for MousePressed to simulate a new round.
    public double getSpeedFactor() {
        return speedFactor;
    }
    public int getAnimalCount() {
        return animalCount;
    }
    public void simulateRound(int animalCount, double speedFactor) {
        //Ensures the board is clear.
        animals.clear();
        //Generates animalCount number of animals.
        for (int i = 0; i < animalCount; i++) {
            //1/4 chance of being each of the following animals: mole, bombbear, friendlybear, and speedysnail.
            int val = (int) (Math.random() * 4);
            Animal animal = null;
            if (val == 0)
            {
                animal = new Animal(window);
            }
            else if (val == 1)
            {
                //speedFactor is incremented after each call to simulateRound(), so snails get 1.5x faster each round.
                animal = new SpeedySnail(window, speedFactor);
            }
            else if (val == 2)
            {
                animal = new BombBear(window);
            }
            else if (val == 3)
            {
                animal = new FriendlyBear(window);
            }
            animals.add(animal);
        }
        //This increase makes each round a bit more time-consuming and difficult.
        this.animalCount += 5;
        this.speedFactor *= 1.5;
    }
    //Called to see if the round is over.
    public boolean isonlyBears() {
        for (Animal a : animals)
        {
            if (!(a instanceof BombBear))
            {
                return false;
            }
        }
        return true;
    }
    public void actionPerformed(ActionEvent e) {
        //Updating the system time to be the end time so that the in game clock is correct.
        endtime = System.currentTimeMillis();
        if (!gameOver) {
                for (Animal a : animals) {
                    //Executes method specific to each class
                    //If I were to add other unique behaviors for animals it would be triggered here,
                    //but for now it really only affects my fish.
                    a.doyourthing();
                }
            window.repaint();
        }
    }
    public ArrayList<Animal> getAnimals() {
        System.out.println(animals);
        return animals;
    }
    public double calculateTotal() {
        return ((endtime - starttime) / 1000);
    }
    public int getRound() {
        return round;
    }
    public void setGameOver() {
        gameOver = true;
    }
    public void addRound() {
        round++;
    }
    public static void main(String[] args) {
        Game a = new Game();
        Timer clock = new Timer(SLEEP_TIME, a);
        clock.start();
    }
}


