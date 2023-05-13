import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game implements ActionListener {
    private GameViewer window;
    private final int NUM_ANIMALS = 20;
    private static final int SLEEP_TIME = 110;
    private ArrayList<Animal> animals;

//    private Player[]
    private int round;
    private int speedFactor;
    private double starttime;
    private double endtime;
    private boolean gameOver;
    private double total;

    private boolean lost;



    private static int ROUND_SIZE = 20;
    private int animalCount;

    public Game() {
        // Initialize the View, then create all the Animals:
        animals = new ArrayList<Animal>();
        this.window = new GameViewer(this);
        speedFactor = 2;
        animalCount = 5;
        round = 1;
        starttime = 0;
        endtime = 0;
        total = 0;
        gameOver = false;
        lost = false;
        run();

    }
    public int getRound() {
        return round;
    }
    public void setGameOver()
    {
        gameOver = true;
    }
    public void addRound() {
        round++;
    }
    public void run()
    {
        starttime = System.currentTimeMillis();
//        System.out.println(starttime);
        simulateRound(animalCount);
    }
    public boolean getGameOver()
    {
        return gameOver;
    }
    public int getAnimalCount() {
        return animalCount;
    }
    public void simulateRound(int animalCount)
    {
        animals.clear();
        for (int i = 0; i < animalCount; i++) {
            int val = (int) (Math.random() * 4);
            //Ok i don't want to have this line because it seems redundant and unnessasry but oh well
            Animal animal = null;
            if (val == 0)
            {
                animal = new Animal(window);
            }
            else if (val == 1)
            {
                animal = new SpeedySnail(window, speedFactor);
            }
            else if (val == 2)
            {
                animal = new BombBear(window, speedFactor);
            }
            else if (val == 3)
            {
                animal = new FriendlyBear(window);
            }
            animals.add(animal);
        }
        this.animalCount += 5;
    }
    public boolean isonlyBears()
    {
        for (Animal a : animals)
        {
            if (!(a instanceof BombBear))
            {
                //need to remove all current bears
                return false;
            }
        }
        return true;
    }
    public void actionPerformed(ActionEvent e) {
        endtime = System.currentTimeMillis();
        if (!gameOver)
        {
                for (Animal a : animals)
                {
                    //executes method specific to each class that does the action
                    a.doyourthing();
                }
                window.repaint();
        }
    }

//    public v

    public ArrayList<Animal> getAnimals() {
        System.out.println(animals);
        return animals;
    }

//    public void g

    public double calculateTotal() {
        return ((endtime - starttime) / 1000);
    }

    public static void main(String[] args) {
        Game a = new Game();
        Timer clock = new Timer(SLEEP_TIME, a);
        clock.start();
    }
}


